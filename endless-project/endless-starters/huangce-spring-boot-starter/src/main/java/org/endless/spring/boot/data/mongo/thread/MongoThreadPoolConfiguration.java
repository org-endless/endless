package org.endless.spring.boot.data.mongo.thread;

import org.endless.spring.boot.common.thread.model.AbstractThreadPoolConfiguration;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * MongoThreadPoolConfiguration
 * <p>Mongo异步线程池配置类
 * <p>The asynchronous thread pool configuration for Mongo.
 * <p>
 * <p>create 2023/5/8 16:15
 * <p>update 2023/9/26 11:32
 *
 * @author Deng Haozhi
 * @see AbstractThreadPoolConfiguration
 * @since 0.0.4
 */
@EnableAsync
@EnableConfigurationProperties(MongoThreadPoolProperties.class)
public class MongoThreadPoolConfiguration extends AbstractThreadPoolConfiguration {

    public MongoThreadPoolConfiguration(MongoThreadPoolProperties mongoThreadPoolProperties) {
        super(mongoThreadPoolProperties);
    }

    @Bean("mongoTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        return super.getAsyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return super.getAsyncUncaughtExceptionHandler();
    }
}
