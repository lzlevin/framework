package com.vf.task.common;

import lombok.Getter;

/**
 * 抽象任务类
 *
 * @author levin
 * @since 1.0.0
 */
@Getter
public abstract class AbstractTask {
    /**
     * 任务名称
     */
    protected String taskName;
    /**
     * 任务类型
     */
    protected String taskType;
    /**
     * 无效标识，置位无效时候则任务无法执行（已经执行不受影响）
     */
    protected boolean invalid;

    /**
     * 初始化任务
     */
    public abstract void init();


    public void go(Object... args) {
        beforeExecute();
        execute(args);
        afterExecute();
    }

    /**
     * 执行前的操作
     */
    protected void beforeExecute() {
    }

    /**
     * 执行任务
     */
    protected abstract void execute(Object... args);

    /**
     * 执行后的操作
     */
    protected void afterExecute() {
    }

    /**
     * 重新配置
     */
    public abstract void reconfigure();

}
