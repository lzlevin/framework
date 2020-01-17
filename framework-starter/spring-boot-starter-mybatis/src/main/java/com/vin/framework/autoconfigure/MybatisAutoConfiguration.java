package com.vin.framework.autoconfigure;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.vin.framework.mybatis.interceptor.EntityHandler;
import com.vin.framework.mybatis.interceptor.EntitySqlInjector;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过spring注入mybatis plus的bean
 *
 * @author levin
 * @since 1.0.0
 */
@Configuration
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
public class MybatisAutoConfiguration {

    @Bean
    public EntitySqlInjector sqlInjector() {
        return new EntitySqlInjector();
    }

    @Bean
    public EntityHandler entityHandler() {
        return new EntityHandler();
    }
}
