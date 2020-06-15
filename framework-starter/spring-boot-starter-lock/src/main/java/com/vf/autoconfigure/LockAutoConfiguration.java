package com.vf.autoconfigure;

import com.vf.lock.aop.LockAnnotationAdvice;
import com.vf.lock.aop.LockAnnotationPointcutAdvisor;
import com.vf.lock.config.LockConfig;
import com.vf.lock.utils.LockHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

/**
 * @author levin
 * @date 2020/6/14
 * @since 1.0.0
 */
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class LockAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "vin.lock")
    public LockConfig lockConfig() {
        return new LockConfig();
    }

    @Bean
    public LockHelper lockHelper(LockConfig lockConfig) {
        return new LockHelper(lockConfig);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public LockAnnotationAdvice lockAnnotationAdvice() {
        return new LockAnnotationAdvice();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public LockAnnotationPointcutAdvisor lockAnnotationPointcutAdvisor(LockAnnotationAdvice advice) {
        return new LockAnnotationPointcutAdvisor(advice);
    }
}
