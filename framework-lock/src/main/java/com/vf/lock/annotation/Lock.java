package com.vf.lock.annotation;

import com.vf.lock.model.LockType;

import java.lang.annotation.*;

/**
 * 锁注解
 *
 * @author levin
 * @date 2020/6/13
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {

    /**
     * 锁
     *
     * @return
     */
    String name() default "";

    /**
     * 锁类型
     *
     * @return 锁定类型
     */
    LockType lockType() default LockType.Reentrant;

    /**
     * 最长等待时间
     *
     * @return 最长等待时间
     */
    int waitTime() default -1;

    /**
     * 锁定时间
     *
     * @return 锁定时间
     */
    int leaseTime() default -1;

    /**
     * spring el表达式，参考{@link org.springframework.cache.annotation.Cacheable#key()}
     *
     * @return spring el表达式
     */
    String spEl() default "";
}
