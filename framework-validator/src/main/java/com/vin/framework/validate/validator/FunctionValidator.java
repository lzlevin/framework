package com.vin.framework.validate.validator;

import com.vin.framework.validate.constraints.Function;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 方法验证，使用{@link java.util.function.Function}
 *
 * @author levin
 * @since 1.0.0
 */
public class FunctionValidator implements ConstraintValidator<Function, Object> {


    String methodName;

    @Override
    public void initialize(Function constraintAnnotation) {
        this.methodName = constraintAnnotation.value();
    }

    //TODO initialize时对方法进行处理加快验证速度

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
        if (null == value || null == methodName) {
            return true;
        }
        try {
            Method declaredMethod = value.getClass().getDeclaredMethod(methodName);
            if (declaredMethod.getReturnType().equals(boolean.class)) {
                boolean result = (boolean) declaredMethod.invoke(value);
                return result;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
