package com.vf.common.validate;

/**
 * 合法性校验接口
 *
 * @author levin
 * @since 1.0.0
 */
public interface Validatable {

    /**
     * 验证参数合法性
     */
    void validate();

    /**
     * 验证参数合法性
     *
     * @param clazz 验证组
     */
    void validate(Class<?>... clazz);
}
