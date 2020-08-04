package com.vf.autoconfigure;

import org.apache.shiro.event.EventBus;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.ShiroEventBusBeanPostProcessor;
import org.apache.shiro.spring.config.AbstractShiroBeanConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br>
 *
 * @author levin
 * @since 1.0
 */
@Configuration
@ConditionalOnProperty(name = "auth.enabled", matchIfMissing = true)
public class AuthBeanAutoConfiguration extends AbstractShiroBeanConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @Override
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return super.lifecycleBeanPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    @Override
    protected EventBus eventBus() {
        return super.eventBus();
    }

    @Bean
    @ConditionalOnMissingBean
    @Override
    public ShiroEventBusBeanPostProcessor shiroEventBusAwareBeanPostProcessor() {
        return super.shiroEventBusAwareBeanPostProcessor();
    }
}
