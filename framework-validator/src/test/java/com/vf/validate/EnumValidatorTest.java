package com.vf.validate;

import com.vf.validate.constraints.Enumerable;
import com.vf.validate.validator.EnumValidator;
import com.vf.validate.constant.MethodNameConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * {@link EnumValidator}进行测试
 * @author levin
 * @since 1.0.0
 */
public class EnumValidatorTest {

    @AllArgsConstructor
    public enum Type {
        A("AAA_KEY", "AAA_VALUE"),
        B("BB_KEY", "BB_VALUE");
        @Getter
        private String key;
        @Getter
        private String value;
    }

    @AllArgsConstructor
    @Data
    public class ValidateEntity {

        @Enumerable(Type.class)
        private String key;

        @Enumerable(Type.class)
        private String errorKey;

        @Enumerable(value = Type.class, method = MethodNameConstant.GET_VALUE)
        private String value;

        @Enumerable(value = Type.class, method = MethodNameConstant.GET_VALUE)
        private String errorValue;

        @Enumerable(value = Type.class, method = "noSuchMethod")
        private String noSuchMethod;
    }

    private Validator validator = null;
    private ValidateEntity entity = null;

    @Before
    public void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        entity = new ValidateEntity(Type.A.getKey(), "ERROR", Type.B.getValue(), "EROOR", "");
    }

    @Test
    public void testValidateKey() {
        Set<ConstraintViolation<ValidateEntity>> key = validator.validateProperty(entity, "key", Default.class);
        Assert.assertEquals(key.size(), 0);
        key = validator.validateProperty(entity, "errorKey", Default.class);
        Assert.assertTrue(key.size() > 0);
    }

    @Test
    public void testValidateValue() {
        Set<ConstraintViolation<ValidateEntity>> key = validator.validateProperty(entity, "value", Default.class);
        Assert.assertEquals(key.size(), 0);
        key = validator.validateProperty(entity, "errorValue", Default.class);
        Assert.assertTrue(key.size() > 0);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateMethod() {
        Set<ConstraintViolation<ValidateEntity>> key = validator.validateProperty(entity, "noSuchMethod", Default.class);
    }


}
