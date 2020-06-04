package com.vf.validate.validator;

import com.vf.validate.exception.ValidationException;

import javax.validation.*;
import javax.validation.groups.Default;
import java.util.Objects;
import java.util.Set;

/**
 * 对bean进行验证
 *
 * @author levin
 * @since 1.0.0
 */
public final class BeanValidator {

    private volatile static Validator validator = null;

    private BeanValidator() {

    }

    /**
     * 获取{@link Validator}的默认实现
     *
     * @return Validator的默认实现
     */
    public static Validator getInstance() {
        if (null == validator) {
            synchronized (BeanValidator.class) {
                if (null == validator) {
                    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                    return validator = factory.getValidator();
                }
            }
        }
        return validator;
    }

    /**
     * 验证object
     *
     * @param object 待验证的bean
     * @throws ValidationException 如果验证失败会抛出其中一个属性的无效说明
     */
    public static void validate(Object object) throws ValidationException {
        validate(object, null);
    }

    /**
     * 验证object
     *
     * @param object 待验证的bean
     * @param clazz  验证组
     * @throws ValidationException 如果object为null或者，验证失败会抛出其中一个属性的无效说明
     */
    public static void validate(Object object, Class<?>... clazz) throws ValidationException {
        if (Objects.isNull(object)) {
            throw new ValidationException("object can not be null");
        }
        if (null == clazz) {
            clazz = new Class[]{Default.class};
        }
        Set<ConstraintViolation<Object>> result = getInstance().validate(object, clazz);
        if (!result.isEmpty()) {
            ConstraintViolation<Object> first = result.iterator().next();
            throw new ValidationException(first.getInvalidValue() + " invalid," + first.getMessage());
        }
    }

    /**
     * Validates all constraints placed on the property of {@code object}
     * named {@code propertyName}.
     *
     * @param object       object to validate
     * @param propertyName property to validate (i.e. field and getter constraints)
     * @param groups       the group or list of groups targeted for validation (defaults to
     *                     {@link Default})
     * @return constraint violations or an empty set if none
     * @throws IllegalArgumentException             if {@code object} is {@code null},
     *                                              if {@code propertyName} is {@code null}, empty or not a valid object property
     *                                              or if {@code null} is passed to the varargs groups
     * @throws javax.validation.ValidationException if a non recoverable error happens
     *                                              during the validation process
     */
    public static <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return getInstance().validateProperty(object, propertyName, groups);
    }

    /**
     * Validates all constraints placed on the property named {@code propertyName}
     * of the class {@code beanType} would the property value be {@code value}.
     * <p>
     * {@link ConstraintViolation} objects return {@code null} for
     * {@link ConstraintViolation#getRootBean()} and
     * {@link ConstraintViolation#getLeafBean()}.
     *
     * @param beanType     the bean type
     * @param propertyName property to validate
     * @param value        property value to validate
     * @param groups       the group or list of groups targeted for validation (defaults to
     *                     {@link Default}).
     * @return constraint violations or an empty set if none
     * @throws IllegalArgumentException             if {@code beanType} is {@code null},
     *                                              if {@code propertyName} is {@code null}, empty or not a valid object property
     *                                              or if {@code null} is passed to the varargs groups
     * @throws javax.validation.ValidationException if a non recoverable error happens
     *                                              during the validation process
     */
    public static <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return getInstance().validateValue(beanType, propertyName, value, groups);
    }
}
