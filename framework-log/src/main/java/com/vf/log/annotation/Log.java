package com.vf.log.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author levin
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块/功能
     *
     * @return 模块/功能
     */
    String function() default "";

    /**
     * 操作
     *
     * @return 操作
     */
    String action() default "";

    /**
     * 描述信息
     *
     * @return 描述信息
     */
    String description() default "";

    /**
     * 忽略日志
     *
     * @return 忽略日志
     */
    boolean ignore() default false;

    /**
     * 记录请求信息
     *
     * @return 记录请求信息
     */
    boolean request() default true;

    /**
     * 记录响应信息
     *
     * @return 响应信息
     */
    boolean response() default true;
}
