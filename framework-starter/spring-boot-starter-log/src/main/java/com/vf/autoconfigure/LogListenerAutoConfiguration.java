package com.vf.autoconfigure;

import com.vf.log.LogPointcutAdvisor;
import com.vf.log.listener.DatasourceLogListener;
import com.vf.log.listener.LogListener;
import com.vf.log.listener.Slf4jLogListener;
import com.vf.log.listener.StdoutLogListener;
import com.vf.log.publisher.LogPublisher;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.lang.reflect.Method;
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
@Order(Ordered.LOWEST_PRECEDENCE)
public class LogListenerAutoConfiguration implements ApplicationListener<ApplicationStartedEvent> {


    @Bean
    @Conditional(StdoutCondition.class)
    public StdoutLogListener vinStdoutLogListener() {
        return new StdoutLogListener();
    }

    @Bean
    @Conditional(Slf4jCondition.class)
    public Slf4jLogListener vinSlf4jLogListener(LogPointcutAdvisor logPointcutAdvisor) {
        Slf4jLogListener slf4jLogListener = new Slf4jLogListener();
        LogPointcutAdvisor.MATCH_METHOD.stream().map(Method::getDeclaringClass).forEach(
                t -> Slf4jLogListener.LOGGER_CACHE.put(t, LoggerFactory.getLogger(t))
        );
        LogPointcutAdvisor.MATCH_METHOD.clear();
        return slf4jLogListener;
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
