package org.dog.server.annotation;

import org.dog.server.convert.Convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Odin
 * @Date: 2023/7/3 23:42
 * @Description:
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordOperate {

    String desc() default "";

    Class<? extends Convert> converter();
}
