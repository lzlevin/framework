package com.vin.framework.validate.constraints;

import com.vin.framework.validate.validator.FunctionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 方法验证，通过class的静态常量进行验证
 *
 * @author levin
 * @since 1.0.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FunctionValidator.class})
public @interface Function {

    String message() default "invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 验证的方法
     *
     * @return 方法名
     */
    String value();

}
