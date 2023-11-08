package org.endless.spring.boot.com.thread;

import org.endless.spring.boot.com.thread.simple.SimpleThreadPoolConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * ThreadAutoConfiguration
 * <p>
 * <p>
 *
 * <p>create 2023/8/12 2:18
 * <p>update 2023/8/12 2:18
 *
 * @author Deng Haozhi
 * @since 0.1.1
 */
@AutoConfiguration
@Import({SimpleThreadPoolConfiguration.class})
public class ThreadAutoConfiguration {
}
