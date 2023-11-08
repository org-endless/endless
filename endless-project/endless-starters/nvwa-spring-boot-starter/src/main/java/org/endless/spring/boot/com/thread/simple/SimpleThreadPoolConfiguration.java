package org.endless.spring.boot.com.thread.simple;

import org.endless.spring.boot.com.thread.model.AbstractThreadPoolConfiguration;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * SimpleThreadPoolConfiguration
 *
 * <p>ERP异步线程池配置类
 *
 * <p>The asynchronous thread pool configuration for EVE.
 *
 * @author Deng Haozhi
 * @date 2023/5/8 16:15
 * @see AbstractThreadPoolConfiguration
 * @since 0.0.2
 */
@EnableAsync
@EnableConfigurationProperties(SimpleThreadPoolProperties.class)
public class SimpleThreadPoolConfiguration extends AbstractThreadPoolConfiguration {

    public SimpleThreadPoolConfiguration(SimpleThreadPoolProperties simpleThreadPoolProperties) {
        super(simpleThreadPoolProperties);
    }

    @Bean("asyncTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        return super.getAsyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return super.getAsyncUncaughtExceptionHandler();
    }
}
