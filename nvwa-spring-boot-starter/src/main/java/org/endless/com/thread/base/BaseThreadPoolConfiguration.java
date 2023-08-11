package org.endless.com.thread.base;

import org.endless.com.thread.model.ThreadPoolConfiguration;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * BaseThreadPoolConfiguration
 *
 * <p>ERP异步线程池配置类
 *
 * <p>The asynchronous thread pool configuration for EVE.
 *
 * @author Deng Haozhi
 * @date 2023/5/8 16:15
 * @see ThreadPoolConfiguration
 * @since 0.0.2
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(BaseThreadPoolProperties.class)
public class BaseThreadPoolConfiguration extends ThreadPoolConfiguration {

    public BaseThreadPoolConfiguration(BaseThreadPoolProperties baseThreadPoolProperties) {
        super(baseThreadPoolProperties);
    }

    @Bean("baseTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        return super.getAsyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return super.getAsyncUncaughtExceptionHandler();
    }
}
