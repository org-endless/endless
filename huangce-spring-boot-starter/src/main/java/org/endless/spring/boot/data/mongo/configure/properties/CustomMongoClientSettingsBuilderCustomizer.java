package org.endless.spring.boot.data.mongo.configure.properties;

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
@EnableConfigurationProperties(CustomMongoProperties.class)
public class CustomMongoClientSettingsBuilderCustomizer {

    private final CustomMongoProperties properties;

    public CustomMongoClientSettingsBuilderCustomizer(CustomMongoProperties properties) {
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
        builder.applyConnectionString(new ConnectionString(properties.getUri()));
        builder.applyToConnectionPoolSettings(settings -> settings
                .minSize(properties.getMinSize())
                .maxSize(properties.getMaxSize())
                .maxWaitTime(properties.getMaxWaitTime(), TimeUnit.MILLISECONDS)
                .maxConnectionLifeTime(properties.getMaxConnectionLifeTime(), TimeUnit.MILLISECONDS)
                .maxConnectionIdleTime(properties.getMaxConnectionIdleTime(), TimeUnit.MILLISECONDS)
                .maintenanceFrequency(properties.getMaintenanceFrequency(), TimeUnit.MILLISECONDS)
                .maintenanceInitialDelay(properties.getMaintenanceInitialDelay(), TimeUnit.MILLISECONDS)
        );
    }
}
