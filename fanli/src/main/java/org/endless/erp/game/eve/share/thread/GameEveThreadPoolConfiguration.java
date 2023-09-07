package org.endless.erp.game.eve.share.thread;

import org.endless.spring.boot.com.thread.model.AbstractThreadPoolConfiguration;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * BaseThreadPoolConfiguration
 * <p>EVE异步线程池配置类
 * <p>The asynchronous thread pool configuration for EVE.
 *
 * @author Deng Haozhi
 * @date 2023/5/8 16:15
 * @see AbstractThreadPoolConfiguration
 * @since 0.0.2
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(GameEveThreadPoolProperties.class)
public class GameEveThreadPoolConfiguration extends AbstractThreadPoolConfiguration {

    public GameEveThreadPoolConfiguration(GameEveThreadPoolProperties gameEveThreadPoolProperties) {
        super(gameEveThreadPoolProperties);
    }

    @Bean("eveTaskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        return super.getAsyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return super.getAsyncUncaughtExceptionHandler();
    }
}
