package com.lierl.springsource.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by lierl
 * 2017/11/26 18:37.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CustomerAnnotation {
    String value() default "";
}
