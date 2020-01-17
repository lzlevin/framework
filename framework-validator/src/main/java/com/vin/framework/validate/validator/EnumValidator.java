package com.vin.framework.validate.validator;

import com.vin.framework.validate.constraints.Enumerable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 注解验证器，通过注解验证key或者value
 * @author levin
 * @since 1.0.0
 */
public class EnumValidator implements ConstraintValidator<Enumerable, Object> {

    private Class<? extends Enum> clazz;

    private Method method;

    @Override
    public void initialize(Enumerable constraintAnnotation) {
        Class<? extends Enum> clazz = constraintAnnotation.value();
        this.clazz = clazz;
        try {
            this.method = clazz.getDeclaredMethod(constraintAnnotation.method());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Enum[] enumConstants = clazz.getEnumConstants();
        for (Enum enumConstant : enumConstants) {
            try {
                Object invoke = method.invoke(enumConstant);
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
