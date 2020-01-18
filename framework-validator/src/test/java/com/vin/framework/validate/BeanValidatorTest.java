package com.vin.framework.validate;

import com.vin.framework.validate.constraints.Enumerable;
import com.vin.framework.validate.exception.ValidationException;
import com.vin.framework.validate.validator.BeanValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * @author levin
 * @since 1.0.0
 */
public class BeanValidatorTest {

    @AllArgsConstructor
    @Data
    public class User {

        @Enumerable(value = EnumValidatorTest.Type.class, message = " user type must be AAA_KEY or BB_KEY")
        private String userType;
    }

    @Test
    public void testUserValid() {
        User user = new User(EnumValidatorTest.Type.A.getKey());
        BeanValidator.validate(user);
    }

    @Test(expected = ValidationException.class)
    public void testUserInvalid() {
        User user = new User("random");
        BeanValidator.validate(user);
    }


}
