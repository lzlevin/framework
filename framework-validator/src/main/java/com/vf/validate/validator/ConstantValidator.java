package com.vf.validate.validator;

import com.vf.validate.constraints.Constant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * 常量验证
 *
 * @author levin
 * @since 1.0.0
 */
public class ConstantValidator implements ConstraintValidator<Constant, Object> {

    private Class<?> clazz;

    @Override
    public void initialize(Constant constraintAnnotation) {
        Class<?> value = constraintAnnotation.value();
        this.clazz = value;
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
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            int modifiers = declaredField.getModifiers();
            boolean validField = Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers);
            if (validField) {
                try {
                    Object o = declaredField.get(null);
                    if (Objects.equals(o, value)) {
                        return true;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }
}
