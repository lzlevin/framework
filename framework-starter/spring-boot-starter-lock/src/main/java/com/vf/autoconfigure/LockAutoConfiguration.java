package com.vf.autoconfigure;

import com.vf.lock.aop.LockAnnotationAdvice;
import com.vf.lock.aop.LockAnnotationPointcutAdvisor;
import com.vf.lock.config.LockConfig;
import com.vf.lock.utils.LockHelper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author levin
 * @date 2020/6/14
 * @since 1.0.0
 */
@Slf4j
public class LockAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "vin.lock")
    public LockConfig lockConfig() {
        return new LockConfig();
    }

    @Bean
    public LockHelper lockHelper(RedissonClient redissonClient, LockConfig lockConfig) {
        return new LockHelper(redissonClient, lockConfig);
    }

    @Bean
    public LockAnnotationAdvice lockAnnotationAdvice(LockHelper lockHelper) {
        return new LockAnnotationAdvice(lockHelper);
    }

    @Bean
    public LockAnnotationPointcutAdvisor lockAnnotationPointcutAdvisor(LockAnnotationAdvice advice) {
        return new LockAnnotationPointcutAdvisor(advice);
    }
}
