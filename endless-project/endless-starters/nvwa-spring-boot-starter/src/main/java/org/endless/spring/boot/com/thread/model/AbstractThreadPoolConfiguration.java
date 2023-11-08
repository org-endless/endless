package org.endless.spring.boot.com.thread.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * AbstractThreadPoolConfiguration
 * <p>异步线程池配置父类
 * <p>The asynchronous thread pool configuration parent class.
 * <p>
 * <p>create 2023/5/8 17:26
 * <p>update 2023/8/15 18:37
 *
 * @author Deng Haozhi
 * @see AsyncConfigurer
 * @since 0.0.2
 */
@Slf4j
public abstract class AbstractThreadPoolConfiguration implements AsyncConfigurer {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    private final AbstractThreadPoolProperties threadPoolProperties;

    public AbstractThreadPoolConfiguration(AbstractThreadPoolProperties threadPoolProperties) {
        this.threadPoolProperties = threadPoolProperties;
    }

    /**
     * 异步线程池初始化
     * <p>Asynchronous thread pool initialization
     *
     * @return java.util.concurrent.Executor
     */
    @Override
    public Executor getAsyncExecutor() {

        threadPoolTaskExecutor.setCorePoolSize(
                Objects.nonNull(threadPoolProperties.getCorePoolSize())
                        ? threadPoolProperties.getCorePoolSize()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_CORE_SIZE);

        threadPoolTaskExecutor.setMaxPoolSize(
                Objects.nonNull(threadPoolProperties.getMaxPoolSize())
                        ? threadPoolProperties.getMaxPoolSize()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_MAX_SIZE);

        threadPoolTaskExecutor.setQueueCapacity(
                Objects.nonNull(threadPoolProperties.getQueueCapacity())
                        ? threadPoolProperties.getQueueCapacity()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_QUEUE_SIZE);

        threadPoolTaskExecutor.setThreadNamePrefix(
                StringUtils.hasLength(threadPoolProperties.getThreadNamePrefix())
                        ? threadPoolProperties.getThreadNamePrefix()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_NAME_PREFIX);

        threadPoolTaskExecutor.setKeepAliveSeconds(
                Objects.nonNull(threadPoolProperties.getKeepAliveSeconds())
                        ? threadPoolProperties.getKeepAliveSeconds()
                        : AbstractThreadPoolProperties.DEFAULT_KEEP_ALIVE_SECONDS);

        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(
                Objects.nonNull(threadPoolProperties.getAllowCoreThreadTimeOut())
                        ? threadPoolProperties.getAllowCoreThreadTimeOut()
                        : AbstractThreadPoolProperties.DEFAULT_ALLOW_CORE_THREAD_TIME_OUT);

        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(
                Objects.nonNull(threadPoolProperties.getWaitForTasksToCompleteOnShutdown())
                        ? threadPoolProperties.getWaitForTasksToCompleteOnShutdown()
                        : AbstractThreadPoolProperties.DEFAULT_WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN);

        threadPoolTaskExecutor.setAwaitTerminationSeconds(
                Objects.nonNull(threadPoolProperties.getAwaitTerminationSeconds())
                        ? threadPoolProperties.getAwaitTerminationSeconds()
                        : AbstractThreadPoolProperties.DEFAULT_AWAIT_TERMINATION_SECONDS);

        var rejectedExecutionHandler = threadPoolProperties.getRejectedExecutionHandler();
        if (StringUtils.hasLength(rejectedExecutionHandler)) {

            if ("AbortPolicy".equals(rejectedExecutionHandler))
                threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

            if ("DiscardPolicy".equals(rejectedExecutionHandler))
                threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

            if ("DiscardOldestPolicy".equals(rejectedExecutionHandler))
                threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

            if ("CallerRunsPolicy".equals(rejectedExecutionHandler))
                threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        } else {
            threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        }
        return threadPoolTaskExecutor;
    }

    /**
     * 异步线程异常捕获
     * <p>Asynchronous thread exception catch
     *
     * @return org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.trace(String.valueOf(method.getDeclaringClass()));
            Arrays.stream(objects).map(object -> "The parameter is: " + object).forEach(log::trace);
            log.error(throwable.getMessage());
        };
    }

    public Integer getCorePoolSize() {
        return threadPoolTaskExecutor.getCorePoolSize();
    }
}
