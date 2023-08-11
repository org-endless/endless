package org.endless.com.thread.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.endless.com.thread.model.ThreadPoolProperties;
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
 * @see ThreadPoolProperties
 * @since 0.0.2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "spring.task.base-thread-pool")
public class BaseThreadPoolProperties extends ThreadPoolProperties {
}
