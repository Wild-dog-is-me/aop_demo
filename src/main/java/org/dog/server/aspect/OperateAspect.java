package org.dog.server.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dog.server.annotation.RecordOperate;
import org.dog.server.convert.Convert;
import org.dog.server.model.OperateLog;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Odin
 * @Date: 2023/7/3 23:45
 * @Description:
 */

@Aspect
@Component
public class OperateAspect {

    @Pointcut("@annotation(org.dog.server.annotation.RecordOperate)")
    public void pointcut() {
    }

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100)
    );

    @Around("pointcut()")
    public Object doOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        threadPoolExecutor.execute(() -> {
            try {

                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                RecordOperate recordOperate = methodSignature.getMethod().getAnnotation(RecordOperate.class);

                Class<? extends Convert> converter = recordOperate.converter();
                OperateLog operateLog = converter.newInstance().convert(joinPoint.getArgs()[0]);

                operateLog.setDesc(recordOperate.desc());
                operateLog.setResult(result.toString());

                System.out.println("insert operateLog " + JSON.toJSONString(operateLog));

            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
        return result;
    }
}
