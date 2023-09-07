package org.endless.spring.boot.com.thread.base;

import org.endless.spring.boot.com.thread.model.AbstractThreadPoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BaseThreadPoolProperties
 *
 * <p>ERP异步线程池配置文件
 *
 * <p>The asynchronous thread pool configuration for ERP
 *
 * @author Deng Haozhi
 * @date 2023/5/8 16:57
 * @see AbstractThreadPoolProperties
 * @since 0.0.2
 */
@ConfigurationProperties(prefix = "spring.task.base-thread-pool")
public class BaseThreadPoolProperties extends AbstractThreadPoolProperties {

    @Override
    public Integer getCorePoolSize() {
        return super.getCorePoolSize();
    }

    @Override
    public Integer getMaxPoolSize() {
        return super.getMaxPoolSize();
    }

    @Override
    public Integer getQueueCapacity() {
        return super.getQueueCapacity();
    }

    @Override
    public String getThreadNamePrefix() {
        return super.getThreadNamePrefix();
    }

    @Override
    public Integer getKeepAliveSeconds() {
        return super.getKeepAliveSeconds();
    }

    @Override
    public Boolean getAllowCoreThreadTimeOut() {
        return super.getAllowCoreThreadTimeOut();
    }

    @Override
    public Boolean getWaitForTasksToCompleteOnShutdown() {
        return super.getWaitForTasksToCompleteOnShutdown();
    }

    @Override
    public Integer getAwaitTerminationSeconds() {
        return super.getAwaitTerminationSeconds();
    }

    @Override
    public String getRejectedExecutionHandler() {
        return super.getRejectedExecutionHandler();
    }

    @Override
    public void setCorePoolSize(Integer corePoolSize) {
        super.setCorePoolSize(corePoolSize);
    }

    @Override
    public void setMaxPoolSize(Integer maxPoolSize) {
        super.setMaxPoolSize(maxPoolSize);
    }

    @Override
    public void setQueueCapacity(Integer queueCapacity) {
        super.setQueueCapacity(queueCapacity);
    }

    @Value("${thread-name-prefix:Base-Thread-Pool-}")
    @Override
    public void setThreadNamePrefix(String threadNamePrefix) {
        super.setThreadNamePrefix(threadNamePrefix);
    }

    @Override
    public void setKeepAliveSeconds(Integer keepAliveSeconds) {
        super.setKeepAliveSeconds(keepAliveSeconds);
    }

    @Override
    public void setAllowCoreThreadTimeOut(Boolean allowCoreThreadTimeOut) {
        super.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
    }

    @Override
    public void setWaitForTasksToCompleteOnShutdown(Boolean waitForTasksToCompleteOnShutdown) {
        super.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
    }

    @Override
    public void setAwaitTerminationSeconds(Integer awaitTerminationSeconds) {
        super.setAwaitTerminationSeconds(awaitTerminationSeconds);
    }

    @Override
    public void setRejectedExecutionHandler(String rejectedExecutionHandler) {
        super.setRejectedExecutionHandler(rejectedExecutionHandler);
    }
}
