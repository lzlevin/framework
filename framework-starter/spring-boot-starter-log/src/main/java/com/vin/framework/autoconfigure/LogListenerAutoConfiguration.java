package com.vin.framework.autoconfigure;

import com.vin.framework.log.listener.DatasourceLogListener;
import com.vin.framework.log.listener.LogListener;
import com.vin.framework.log.listener.Slf4jLogListener;
import com.vin.framework.log.listener.StdoutLogListener;
import com.vin.framework.log.publisher.LogPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 日志监听配置
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
@Configuration
public class LogListenerAutoConfiguration implements ApplicationListener<ApplicationStartedEvent> {

    @Bean
    @Conditional(StdoutCondition.class)
    public StdoutLogListener vinStdoutLogListener() {
        return new StdoutLogListener();
    }

    @Bean
    @Conditional(Slf4jCondition.class)
    public Slf4jLogListener vinSlf4jLogListener() {
        return new Slf4jLogListener();
    }

    @Bean
    @Conditional(DatabaseCondition.class)
    @ConditionalOnClass(DataSource.class)
    public DatasourceLogListener vinDatasourceLogListener(DataSource dataSource) {
        return new DatasourceLogListener(dataSource);
    }

    /**
     * 容器启动完成后注入监听器
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LogPublisher publisher = event.getApplicationContext().getBean(LogPublisher.class);
        Map<String, LogListener> beans = event.getApplicationContext().getBeansOfType(LogListener.class);
        List<LogListener> logListenerStream = beans.values().stream().filter(t -> !(t instanceof LogPublisher)).collect(Collectors.toList());
        logListenerStream.forEach(publisher::addListener);
        String clazz = logListenerStream.stream().map(t -> t.getClass().getSimpleName()).collect(Collectors.joining(","));
        log.info("Add listeners " + clazz);
    }
}
