package org.endless.com.thread.model;

import lombok.extern.log4j.Log4j2;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPoolConfiguration
 *
 * <p>异步线程池配置父类
 *
 * <p>The asynchronous thread pool configuration parent class.
 *
 * @author Deng Haozhi
 * @date 2023/5/8 17:26
 * @see ThreadPoolProperties
 * @since 0.0.2
 */
@Log4j2
public class ThreadPoolConfiguration implements AsyncConfigurer {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    private final ThreadPoolProperties threadPoolProperties;

    public ThreadPoolConfiguration(ThreadPoolProperties threadPoolProperties) {
        this.threadPoolProperties = threadPoolProperties;
    }

    @Bean
    public ThreadPoolProperties threadPoolProperties() {
        return new ThreadPoolProperties();
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
                Objects.nonNull(threadPoolProperties.getCorePoolSize())
                        ? threadPoolProperties.getCorePoolSize()
                        : ThreadPoolProperties.DEFAULT_THREAD_CORE_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(
                Objects.nonNull(threadPoolProperties.getMaxPoolSize())
                        ? threadPoolProperties.getMaxPoolSize()
                        : ThreadPoolProperties.DEFAULT_THREAD_MAX_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(
                Objects.nonNull(threadPoolProperties.getQueueCapacity())
                        ? threadPoolProperties.getQueueCapacity()
                        : ThreadPoolProperties.DEFAULT_THREAD_QUEUE_SIZE);
        threadPoolTaskExecutor.setThreadNamePrefix(
                Objects.nonNull(threadPoolProperties.getThreadNamePrefix())
                        && !threadPoolProperties.getThreadNamePrefix().isEmpty()
                        ? threadPoolProperties.getThreadNamePrefix()
                        : ThreadPoolProperties.DEFAULT_THREAD_NAME_PREFIX);
        threadPoolTaskExecutor.setKeepAliveSeconds(
                Objects.nonNull(threadPoolProperties.getKeepAliveSeconds())
                        ? threadPoolProperties.getKeepAliveSeconds()
                        : ThreadPoolProperties.DEFAULT_KEEP_ALIVE_SECONDS);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(
                Objects.nonNull(threadPoolProperties.getAllowCoreThreadTimeOut())
                        ? threadPoolProperties.getAllowCoreThreadTimeOut()
                        : ThreadPoolProperties.DEFAULT_ALLOW_CORE_THREAD_TIME_OUT);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(
                Objects.nonNull(threadPoolProperties.getWaitForTasksToCompleteOnShutdown())
                        ? threadPoolProperties.getWaitForTasksToCompleteOnShutdown()
                        : ThreadPoolProperties.DEFAULT_WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(
                Objects.nonNull(threadPoolProperties.getAwaitTerminationSeconds())
                        ? threadPoolProperties.getAwaitTerminationSeconds()
                        : ThreadPoolProperties.DEFAULT_AWAIT_TERMINATION_SECONDS);

        if (!Objects.nonNull(threadPoolProperties.getRejectedExecutionHandler())
                || threadPoolProperties.getRejectedExecutionHandler().isEmpty()
                || threadPoolProperties.getRejectedExecutionHandler().equals("AbortPolicy"))
            threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        if (Objects.nonNull(threadPoolProperties.getRejectedExecutionHandler())
                && threadPoolProperties.getRejectedExecutionHandler().equals("DiscardPolicy"))
            threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        if (Objects.nonNull(threadPoolProperties.getRejectedExecutionHandler())
                && threadPoolProperties.getRejectedExecutionHandler().equals("DiscardOldestPolicy"))
            threadPoolTaskExecutor.setRejectedExecutionHandler(
                    new ThreadPoolExecutor.DiscardOldestPolicy());
        if (Objects.nonNull(threadPoolProperties.getRejectedExecutionHandler())
                && threadPoolProperties.getRejectedExecutionHandler().equals("CallerRunsPolicy"))
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
            log.error(method.getDeclaringClass());
            Arrays.stream(objects).map(object -> "The parameter is: " + object).forEach(log::debug);
            log.debug(throwable.getStackTrace());
        };
    }

    public Integer getCorePoolSize() {
        return threadPoolTaskExecutor.getCorePoolSize();
    }
}
