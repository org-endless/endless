// package org.endless.spring.boot.common.thread.simple;
//
// import lombok.Getter;
// import lombok.Setter;
// import org.endless.spring.boot.common.thread.model.AbstractThreadPoolProperties;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.context.properties.ConfigurationProperties;
//
// /**
//  * SimpleThreadPoolProperties
//  * <p>异步线程池配置文件
//  * <p>The asynchronous thread pool configuration.
//  * <p>
//  * <p>create 2023/5/8 16:57
//  * <p>update 2023/9/25 20:48
//  *
//  * @author Deng Haozhi
//  * @see AbstractThreadPoolProperties
//  * @since 0.0.4
//  */
// @Getter
// @Setter
// @ConfigurationProperties(prefix = "spring.task.async-thread-pool")
// public class SimpleThreadPoolProperties extends AbstractThreadPoolProperties {
//
//     @Value("${thread-name-prefix:Async-Thread-Pool-}")
//     @Override
//     public void setThreadNamePrefix(String threadNamePrefix) {
//         super.setThreadNamePrefix(threadNamePrefix);
//     }
// }
