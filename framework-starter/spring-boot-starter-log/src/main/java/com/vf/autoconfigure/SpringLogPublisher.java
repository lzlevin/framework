package com.vf.autoconfigure;

import com.vf.log.event.LogAfterEvent;
import com.vf.log.event.LogAfterThrowingEvent;
import com.vf.log.event.LogBeforeEvent;
import com.vf.log.listener.LogListener;
import com.vf.log.publisher.LogPublisher;
import com.vf.log.publisher.SimpleLogPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 日志发布器
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
public class SpringLogPublisher extends SimpleLogPublisher implements LogPublisher, ApplicationEventPublisherAware, DisposableBean, ApplicationListener<ApplicationStartedEvent> {
    /**
     * 线程池
     */
    private ExecutorService threadPoolExecutor;

    /**
     * 配置文件
     */
    private final LogConfiguration configuration;


    public SpringLogPublisher(LogConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * spring事件发布器
     */
    private ApplicationEventPublisher eventPublisher;


    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    @Override
    public void after(LogAfterEvent event) {
        getSyncListeners().forEach(t -> t.after(event));
        if (getAsyncListeners().size() > 0) {
            List<AfterCallable> collect = getAsyncListeners().stream().map(t -> new AfterCallable(t, event)).collect(Collectors.toList());
            try {
                threadPoolExecutor.invokeAll(collect, configuration.getTimeout(), TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("log thread pool execute error.");
            }
        }
        eventPublisher.publishEvent(event);
    }

    /**
     * 执行前的日志记录
     *
     * @param event 日志事件
     */
    @Override
    public void before(LogBeforeEvent event) {
        getSyncListeners().forEach(t -> t.before(event));
        if (getAsyncListeners().size() > 0) {
            List<BeforeCallable> collect = getAsyncListeners().stream().map(t -> new BeforeCallable(t, event)).collect(Collectors.toList());
            try {
                threadPoolExecutor.invokeAll(collect, configuration.getTimeout(), TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("log thread pool execute error.");
            }
        }
        eventPublisher.publishEvent(event);
    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    @Override
    public void afterThrowing(LogAfterThrowingEvent event) {
        getSyncListeners().forEach(t -> t.afterThrowing(event));
        if (getAsyncListeners().size() > 0) {
            List<AfterThrowingCallable> collect = getAsyncListeners().stream().map(t -> new AfterThrowingCallable(t, event)).collect(Collectors.toList());
            try {
                threadPoolExecutor.invokeAll(collect, configuration.getTimeout(), TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("log thread pool execute error.");
            }
        }
        eventPublisher.publishEvent(event);
    }

    /**
     * Set the ApplicationEventPublisher that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's afterPropertiesSet or a custom init-method.
     * Invoked before ApplicationContextAware's setApplicationContext.
     *
     * @param applicationEventPublisher event publisher to be used by this object
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        threadPoolExecutor.shutdown();
    }

    /**
     * 容器启动后初始化线程池
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        threadPoolExecutor = new ThreadPoolExecutor(configuration.getCorePoolSize(), configuration.getMaxPoolSize(), 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy());
        log.info("Initializing ExecutorService 'vinLogExecutor'");
    }


    /**
     * 事件task
     */
    class BeforeCallable implements Callable<Object> {

        private LogListener logListener;
        private LogBeforeEvent event;

        public BeforeCallable(LogListener logListener, LogBeforeEvent event) {
            this.logListener = logListener;
            this.event = event;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Object call() throws Exception {
            logListener.before(event);
            return null;
        }
    }


    /**
     * 事件task
     */
    class AfterCallable implements Callable<Object> {

        private LogListener logListener;
        private LogAfterEvent event;

        public AfterCallable(LogListener logListener, LogAfterEvent event) {
            this.logListener = logListener;
            this.event = event;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Object call() throws Exception {
            logListener.after(event);
            return null;
        }
    }


    /**
     * 事件task
     */
    class AfterThrowingCallable implements Callable<Object> {

        private LogListener logListener;
        private LogAfterThrowingEvent event;

        public AfterThrowingCallable(LogListener logListener, LogAfterThrowingEvent event) {
            this.logListener = logListener;
            this.event = event;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Object call() throws Exception {
            logListener.afterThrowing(event);
            return null;
        }
    }
}
