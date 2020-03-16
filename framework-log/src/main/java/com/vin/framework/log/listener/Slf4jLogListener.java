package com.vin.framework.log.listener;

import com.vin.framework.log.event.LogAfterEvent;
import com.vin.framework.log.event.LogAfterThrowingEvent;
import com.vin.framework.log.event.LogBeforeEvent;
import com.vin.framework.log.event.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.WeakHashMap;

/**
 * {@link org.slf4j.Logger}的监听器
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
public class Slf4jLogListener implements LogListener {

    WeakHashMap<Class, Logger> loggerCache = new WeakHashMap<>();

    /**
     * 执行前的日志记录
     *
     * @param event 日志事件
     */
    @Override
    public void before(LogBeforeEvent event) {
        if (null == event.getClazz()) {
            log.info(event.toString());
        } else {
            Logger logger = getLogger(event);
            logger.info(event.toString());
        }
    }

    /**
     * 获取日志对象
     *
     * @param event 时间
     * @return
     */
    private Logger getLogger(LogEvent event) {
        Logger logger = loggerCache.get(event.getClazz());
        if (logger == null) {
            logger = LoggerFactory.getLogger(event.getClazz());
            loggerCache.put(event.getClazz(), logger);
        }
        return logger;
    }

    /**
     * 执行异常的日志记录
     *
     * @param event 日志记录
     */
    @Override
    public void afterThrowing(LogAfterThrowingEvent event) {
        if (null == event.getClazz()) {
            log.error(event.toString(), event.getThrowable());
        } else {
            Logger logger = getLogger(event);
            logger.error(event.toString(), event.getThrowable());
        }
    }

    /**
     * 执行完毕后的日志记录
     *
     * @param event
     */
    @Override
    public void after(LogAfterEvent event) {
        if (null == event.getClazz()) {
            log.info(event.toString());
        } else {
            Logger logger = getLogger(event);
            logger.info(event.toString());
        }
    }

    /**
     * 获取顺序，顺序越大越先执行
     *
     * @return 顺序，用于触发次序
     */
    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 10;
    }
}
