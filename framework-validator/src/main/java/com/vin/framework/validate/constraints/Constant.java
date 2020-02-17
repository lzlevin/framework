package com.vin.framework.validate.constraints;

import com.vin.framework.validate.validator.ConstantValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 常量验证，通过class的静态常量进行验证
 *
 * @author levin
 * @since 1.0.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ConstantValidator.class})
public @interface Constant {

    String message() default "Property must in constant";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 验证的常量类
     *
     * @return 常量类
     */
    Class<?> value();

}
