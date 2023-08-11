package org.endless.com.thread.model;

import lombok.Data;

/**
 * ThreadPoolProperties
 *
 * <p>异步线程池配置文件父类
 *
 * <p>The asynchronous thread pool configuration file parent class
 *
 * @author Deng Haozhi
 * @date 2023/5/8 17:22
 * @see ThreadPoolConfiguration
 * @since 0.0.2
 */
@Data
public class ThreadPoolProperties {

    /*
     * Thread default arguments
     */
    public static final Integer CPUS = Runtime.getRuntime().availableProcessors();

    public static final Integer DEFAULT_THREAD_CORE_SIZE = CPUS * 2;

    public static final Integer DEFAULT_THREAD_MAX_SIZE = DEFAULT_THREAD_CORE_SIZE * 2;

    public static final Integer DEFAULT_THREAD_QUEUE_SIZE = DEFAULT_THREAD_MAX_SIZE * 2;

    public static final String DEFAULT_THREAD_NAME_PREFIX = "Async-Thread-Pool-";

    public static final Integer DEFAULT_KEEP_ALIVE_SECONDS = 60;

    public static final Boolean DEFAULT_ALLOW_CORE_THREAD_TIME_OUT = false;

    public static final Boolean DEFAULT_WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN = false;

    public static final Integer DEFAULT_AWAIT_TERMINATION_SECONDS = 10;

    /**
     * 核心线程数，默认 可使用处理器数*2
     *
     * <p>Core pool size. Default: CPUS*2
     */
    private Integer corePoolSize;

    /**
     * 最大线程数，默认 核心线程数*2
     *
     * <p>Max pool size. Default: THREAD_CORE_SIZE*2
     */
    private Integer maxPoolSize;

    /**
     * 队列深度,默认 最大线程数*2
     *
     * <p>Depth of queue. Default: THREAD_MAX_SIZE*2
     */
    private Integer queueCapacity;

    /**
     * 自定义线程名前缀，默认 Async-ThreadPool-
     *
     * <p>Thread name prefix. Default: Async-ThreadPool-
     */
    private String threadNamePrefix;

    /**
     * 空闲线程存活时间，单位秒，默认 60
     *
     * <p>Idle threads keep alive seconds. Default: 60
     */
    private Integer keepAliveSeconds;

    /**
     * 核心线程是否允许超时，默认 false
     *
     * <p>Whether the core thread allows timeouts. Default: false
     */
    private Boolean allowCoreThreadTimeOut;

    /**
     * IOC容器关闭时是否等待未完成任务，默认 false
     *
     * <p>与awaitTerminationSeconds同时配置
     *
     * <p>Whether to wait for outstanding tasks when the IOC container is closed. Default: false
     *
     * <p>Configured at the same time as awaitTerminationSeconds.
     */
    private Boolean waitForTasksToCompleteOnShutdown;

    /**
     * IOC容器关闭时未完成任务等待时间，单位秒，默认 10
     *
     * <p>与waitForTasksToCompleteOnShutdown同时配置
     *
     * <p>The waiting seconds for incomplete tasks when the IOC container is closed. Default: false
     *
     * <p>Configured at the same time as waitForTasksToCompleteOnShutdown.
     */
    private Integer awaitTerminationSeconds;

    /**
     * 拒绝策略，默认 AbortPolicy
     *
     * <p>Deny policy. Default: AbortPolicy
     *
     * <p>AbortPolicy: 丢弃任务并抛出RejectedExecutionException异常
     *
     * <p>DiscardPolicy: 丢弃任务但不抛出异常
     *
     * <p>DiscardOldestPolicy: 丢弃最旧的处理程序，然后重试，如果执行器关闭，这时丢弃任务
     *
     * <p>CallerRunsPolicy: 执行器执行任务失败，则在策略回调方法中执行任务，如果执行器关闭，这时丢弃任务
     */
    private String rejectedExecutionHandler;
}
