package org.endless.data.mongo.configure.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * MongoClient配置文件类<p>
 * Endless MongoClient Properties
 * <p>create 2023/01/26 17:05
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class CustomMongoClientProperties extends MongoProperties {

    /**
     * 最小连接池数<P>
     * ConnectionPoolSettings minSize
     * Sets the minimum amount of connections associated with a connection pool.
     */
    @Value("${min-size:1}")
    private Integer minSize;

    /**
     * ConnectionPoolSettings.maxSize
     * Sets the maximum amount of connections associated with a connection pool.
     */
    @Value("${max-size:200}")
    private Integer maxSize;

    /**
     * ConnectionPoolSettings.maxWaitTimeMS
     * Sets the maximum time to wait for an available connection.
     */
    @Value("${max-wait-time:120000}")
    private Integer maxWaitTime;

    /**
     * ConnectionPoolSettings.maxConnectionLifeTimeMS
     * Sets the maximum time a pooled connection can be alive before it's closed.
     */
    @Value("${max-connection-life-time:0}")
    private Integer maxConnectionLifeTime;

    /**
     * ConnectionPoolSettings.maxConnectionIdleTimeMS
     * Sets the maximum time a connection can be idle before it's closed.
     */
    @Value("${max-connection-idle-time:0}")
    private Integer maxConnectionIdleTime;

    /**
     * ConnectionPoolSettings.maintenanceFrequencyMS
     * Sets the frequency for running a maintenance job.
     */
    @Value("${maintenance-frequency:60000}")
    private Integer maintenanceFrequency;

    /**
     * ConnectionPoolSettings.maintenanceFrequency
     * Sets the time to wait before running the first maintenance job.
     */
    @Value("${maintenance-initial-delay:0}")
    private Integer maintenanceInitialDelay;

}
