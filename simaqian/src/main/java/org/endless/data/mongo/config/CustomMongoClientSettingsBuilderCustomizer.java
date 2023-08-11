package org.endless.data.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * CustomMongoClientSettingsBuilderCustomizer
 * create 2022/12/27 11:04
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */
@EnableConfigurationProperties(CustomMongoClientProperties.class)
public class CustomMongoClientSettingsBuilderCustomizer {

    private final CustomMongoClientProperties properties;

    public CustomMongoClientSettingsBuilderCustomizer(CustomMongoClientProperties properties) {
        this.properties = properties;
    }

    public void customize(MongoClientSettings.Builder builder) {
        applyToConnectionPoolSettings(builder);
    }

    /**
     * @MethodName applyToConnectionPoolSettings
     * @Description MILLISECONDS
     * @Param [builder]
     * @Retrun void
     * @Author EndlessError
     * @Date 2022/12/28 2:10
     * @Version
     */
    public void applyToConnectionPoolSettings(MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString("mongodb://1.15.236.69:27017/endless-erp"));
        builder.applyToConnectionPoolSettings(settings -> settings
                .minSize(this.properties.getMinSize())
                .maxSize(this.properties.getMaxSize())
                .maxWaitTime(this.properties.getMaxWaitTime(), TimeUnit.MILLISECONDS)
                .maxConnectionLifeTime(this.properties.getMaxConnectionLifeTime(), TimeUnit.MILLISECONDS)
                .maxConnectionIdleTime(this.properties.getMaxConnectionIdleTime(), TimeUnit.MILLISECONDS)
                .maintenanceFrequency(this.properties.getMaintenanceFrequency(), TimeUnit.MILLISECONDS)
                .maintenanceInitialDelay(this.properties.getMaintenanceInitialDelay(), TimeUnit.MILLISECONDS)
        );
    }

    private <V> V getOrDefault(V value, V defaultValue) {
        return (value != null) ? value : defaultValue;
    }

}
