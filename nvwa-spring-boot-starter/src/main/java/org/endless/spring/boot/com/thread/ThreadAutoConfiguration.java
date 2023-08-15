package org.endless.spring.boot.com.thread;

import org.endless.spring.boot.com.thread.base.BaseThreadPoolConfiguration;
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
@Import(BaseThreadPoolConfiguration.class)
public class ThreadAutoConfiguration {
}
