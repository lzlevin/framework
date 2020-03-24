package com.vf.autoconfigure;

import com.vf.log.LogInterceptor;
import com.vf.log.LogPointcutAdvisor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志自动配置
 *
 * @author levin
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({LogConfiguration.class})
public class LogAutoConfiguration {

    @Bean
    public SpringLogPublisher vinSpringLogPublisher(LogConfiguration configuration) {
        SpringLogPublisher springLogPublisher = new SpringLogPublisher(configuration);
        return springLogPublisher;
    }

    @Bean
    public LogPointcutAdvisor vinLogPointcutAdvisor(SpringLogPublisher springLogPublisher) {
        LogInterceptor logInterceptor = new LogInterceptor(springLogPublisher);
        return new LogPointcutAdvisor(logInterceptor);
    }
}
