package com.vf.log.listener;

import com.vf.log.event.LogAfterThrowingEvent;
import com.vf.log.event.LogAfterEvent;
import com.vf.log.event.LogBeforeEvent;
import com.vf.log.event.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link org.slf4j.Logger}的监听器
 *
 * @author levin
 * @since 1.0.0
 */
@Slf4j
public class Slf4jLogListener implements LogListener {

    public static final Map<Class, Logger> LOGGER_CACHE = new HashMap<>();

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
        Logger logger = LOGGER_CACHE.get(event.getClazz());
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
