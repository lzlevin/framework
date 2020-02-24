package com.vin.framework.autoconfigure;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
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

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(100);
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
