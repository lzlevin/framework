package com.vf.validate.constraints;

import com.vf.validate.validator.EnumValidator;
import com.vf.validate.constant.MethodNameConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * 通过注解进行验证
 * @author levin
 * @since 1.0.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValidator.class})
public @interface Enumerable {

    String message() default "Property must be enumerable and valid getter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 验证的枚举类
     * @return 枚举类
     */
    Class<? extends java.lang.Enum> value();

    /**
     * 验证的值的取值方法
     * @return 方法名
     */
    String method() default MethodNameConstant.GET_KEY;
}
