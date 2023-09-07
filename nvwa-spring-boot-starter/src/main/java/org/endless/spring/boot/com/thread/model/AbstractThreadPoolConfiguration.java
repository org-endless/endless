package org.endless.spring.boot.com.thread.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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

    private final AbstractThreadPoolProperties abstractThreadPoolProperties;

    public AbstractThreadPoolConfiguration(AbstractThreadPoolProperties abstractThreadPoolProperties) {
        this.abstractThreadPoolProperties = abstractThreadPoolProperties;
    }

    /**
     * 异步线程池初始化
     *
     * <p>Asynchronous thread pool initialization
     *
     * @return java.util.concurrent.Executor
     */
    @Override
    public Executor getAsyncExecutor() {

        threadPoolTaskExecutor.setCorePoolSize(
                Objects.nonNull(abstractThreadPoolProperties.getCorePoolSize())
                        ? abstractThreadPoolProperties.getCorePoolSize()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_CORE_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(
                Objects.nonNull(abstractThreadPoolProperties.getMaxPoolSize())
                        ? abstractThreadPoolProperties.getMaxPoolSize()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_MAX_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(
                Objects.nonNull(abstractThreadPoolProperties.getQueueCapacity())
                        ? abstractThreadPoolProperties.getQueueCapacity()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_QUEUE_SIZE);
        threadPoolTaskExecutor.setThreadNamePrefix(
                Objects.nonNull(abstractThreadPoolProperties.getThreadNamePrefix())
                        && !abstractThreadPoolProperties.getThreadNamePrefix().isEmpty()
                        ? abstractThreadPoolProperties.getThreadNamePrefix()
                        : AbstractThreadPoolProperties.DEFAULT_THREAD_NAME_PREFIX);
        threadPoolTaskExecutor.setKeepAliveSeconds(
                Objects.nonNull(abstractThreadPoolProperties.getKeepAliveSeconds())
                        ? abstractThreadPoolProperties.getKeepAliveSeconds()
                        : AbstractThreadPoolProperties.DEFAULT_KEEP_ALIVE_SECONDS);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(
                Objects.nonNull(abstractThreadPoolProperties.getAllowCoreThreadTimeOut())
                        ? abstractThreadPoolProperties.getAllowCoreThreadTimeOut()
                        : AbstractThreadPoolProperties.DEFAULT_ALLOW_CORE_THREAD_TIME_OUT);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(
                Objects.nonNull(abstractThreadPoolProperties.getWaitForTasksToCompleteOnShutdown())
                        ? abstractThreadPoolProperties.getWaitForTasksToCompleteOnShutdown()
                        : AbstractThreadPoolProperties.DEFAULT_WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(
                Objects.nonNull(abstractThreadPoolProperties.getAwaitTerminationSeconds())
                        ? abstractThreadPoolProperties.getAwaitTerminationSeconds()
                        : AbstractThreadPoolProperties.DEFAULT_AWAIT_TERMINATION_SECONDS);

        if (!Objects.nonNull(abstractThreadPoolProperties.getRejectedExecutionHandler())
                || abstractThreadPoolProperties.getRejectedExecutionHandler().isEmpty()
                || abstractThreadPoolProperties.getRejectedExecutionHandler().equals("AbortPolicy"))
            threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        if (Objects.nonNull(abstractThreadPoolProperties.getRejectedExecutionHandler())
                && abstractThreadPoolProperties.getRejectedExecutionHandler().equals("DiscardPolicy"))
            threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        if (Objects.nonNull(abstractThreadPoolProperties.getRejectedExecutionHandler())
                && abstractThreadPoolProperties.getRejectedExecutionHandler().equals("DiscardOldestPolicy"))
            threadPoolTaskExecutor.setRejectedExecutionHandler(
                    new ThreadPoolExecutor.DiscardOldestPolicy());
        if (Objects.nonNull(abstractThreadPoolProperties.getRejectedExecutionHandler())
                && abstractThreadPoolProperties.getRejectedExecutionHandler().equals("CallerRunsPolicy"))
            threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return threadPoolTaskExecutor;
    }

    /**
     * 异步线程异常捕获
     *
     * <p>Asynchronous thread exception catch
     *
     * @return org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            // log.error(String.valueOf(method.getDeclaringClass()));
            Arrays.stream(objects).map(object -> "The parameter is: " + object).forEach(log::trace);
            log.error(throwable.getMessage());
        };
    }

    public Integer getCorePoolSize() {
        return threadPoolTaskExecutor.getCorePoolSize();
    }
}
