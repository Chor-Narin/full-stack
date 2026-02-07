package com.chornarin.site.full_stack.annotations;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimited {
    long limit() default 5;      // max requests
    long duration() default 60;  // in seconds
}
