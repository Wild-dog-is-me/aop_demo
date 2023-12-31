package org.dog.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * @Author: Odin
 * @Date: 2023/7/3 20:44
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * <a href="https://blog.csdn.net/yangqinfeng1121/article/details/110368622">Spring中切面的@pointcut中execution总结</a>
     */
    @Pointcut("execution(* org.dog.server.controller.*.*(..))")
    private void pointCutMethod() {

    }

    /**
     * 前置通知.
     */
    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置通知@Before执行");
    }

    /**
     * 环绕通知.
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        log.info("-----------------------");
        log.info("---Around日志通知: 进入类: {}", pjp.getTarget().getClass().getName());
        log.info("---Around通知: 进入方法: {}", methodSignature.getName());
        log.info("---Around通知: 参数列表: {}", Arrays.toString(pjp.getArgs()));
        Object o = pjp.proceed();
        watch.stop();
        log.info("接口耗时: {}", formatTime(watch.getTotalTimeMillis()));
        log.info("-----------------------");
        return o;
    }

    /**
     * 后置通知.
     *
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        log.info("---后置通知: 返回值: {}", result);
    }


    /**
     * 异常通知.
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        log.info("异常通知, 异常: {}", e.getMessage());
        log.info("-----------------------");
    }

    /**
     * 最终通知.
     */
    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("@After最终通知执行");
    }

    /**
     * 毫秒转时分秒
     **/
    public static String formatTime(Long ms) {
        int ss = 1000;
        int mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond).append("毫秒");
        }
        return sb.toString();
    }
}
