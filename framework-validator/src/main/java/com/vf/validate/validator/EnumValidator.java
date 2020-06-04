package com.vf.validate.validator;

import com.vf.validate.constraints.Enumerable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 注解验证器，通过注解验证key或者value
 *
 * @author levin
 * @since 1.0.0
 */
public class EnumValidator implements ConstraintValidator<Enumerable, Object> {

    private Class<? extends Enum> clazz;

    private String method;

    @Override
    public void initialize(Enumerable constraintAnnotation) {
        Class<? extends Enum> clazz = constraintAnnotation.value();
        this.clazz = clazz;
        this.method = constraintAnnotation.method();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == clazz || null == value) {
            return true;
        }
        Enum[] enumConstants = clazz.getEnumConstants();
        Method declaredMethod = null;
        try {
            declaredMethod = clazz.getDeclaredMethod(this.method);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        for (Enum enumConstant : enumConstants) {
            try {
                Object invoke = declaredMethod.invoke(enumConstant);
                if (Objects.equals(value, invoke)) {
                    return true;
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
