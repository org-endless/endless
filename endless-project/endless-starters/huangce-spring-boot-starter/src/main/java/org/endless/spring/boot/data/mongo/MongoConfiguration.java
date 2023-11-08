package org.endless.spring.boot.data.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.endless.spring.boot.data.mongo.connection.MongoConnectionPoolProperties;
import org.endless.spring.boot.data.mongo.decimal128.FromDecimal128Converter;
import org.endless.spring.boot.data.mongo.decimal128.ToDecimal128Converter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoConnectionDetails;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * MongoConfiguration
 * <p>Mongo 配置类
 * <p>Mongo configuration class.
 * <p>
 * <p>create 2023/01/26 16:33
 * <p>update 2023/8/15 16:01
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
@EnableConfigurationProperties(MongoConnectionPoolProperties.class)
public class MongoConfiguration {

    private final MongoConnectionPoolProperties connectionPoolProperties;

    public MongoConfiguration(MongoConnectionPoolProperties connectionPoolProperties) {
        this.connectionPoolProperties = connectionPoolProperties;
    }


    @ConditionalOnMissingBean(MongoOperations.class)
    public @Bean MongoTemplate mongoTemplate(
            @Qualifier("mongoCustomConversions") MongoCustomConversions mongoCustomConversions,
            @Qualifier("mongoDatabaseFactory") MongoDatabaseFactory mongoDatabaseFactory) {

        var mongoTemplate = new MongoTemplate(mongoDatabaseFactory);
        var mappingMongoConverter = (MappingMongoConverter) mongoTemplate.getConverter();
        mappingMongoConverter.setCustomConversions(mongoCustomConversions);
        mappingMongoConverter.afterPropertiesSet();
        return mongoTemplate;
    }

    @ConditionalOnMissingBean(MongoTransactionManager.class)
    public @Bean MongoTransactionManager mongoTransactionManager(
            @Qualifier("mongoDatabaseFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }

    @ConditionalOnMissingBean(MongoDatabaseFactory.class)
    public @Bean MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("mongoClient") MongoClient mongoClient,
            @Qualifier("connectionString") ConnectionString connectionString) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, Objects.requireNonNull(connectionString.getDatabase()));
    }

    @ConditionalOnMissingBean(MongoClient.class)
    public @Bean MongoClient mongoClient(
            @Qualifier("connectionString") ConnectionString connectionString) {
        return MongoClients.create(applyToConnectionPoolSettings(
                MongoClientSettings.builder())
                .applyConnectionString(connectionString).build());
    }

    @ConditionalOnMissingBean(ConnectionString.class)
    public @Bean ConnectionString connectionString(
            @Qualifier("mongoConnectionDetails") MongoConnectionDetails connectionDetails) {
        return connectionDetails.getConnectionString();
    }

    @ConditionalOnMissingBean(MongoCustomConversions.class)
    public @Bean MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(
                List.of(new FromDecimal128Converter(), new ToDecimal128Converter()));
    }

    public MongoClientSettings.Builder applyToConnectionPoolSettings(MongoClientSettings.Builder builder) {

        return builder.applyToConnectionPoolSettings(settings -> settings
                .minSize(connectionPoolProperties.getMinPoolSize())
                .maxSize(connectionPoolProperties.getMaxPoolSize())
                .maxWaitTime(connectionPoolProperties.getMaxConnectionWaitTime(), TimeUnit.MILLISECONDS)
                .maxConnectionLifeTime(connectionPoolProperties.getMaxConnectionLifeTime(), TimeUnit.MILLISECONDS)
                .maxConnectionIdleTime(connectionPoolProperties.getMaxConnectionIdleTime(), TimeUnit.MILLISECONDS)
                .maintenanceFrequency(connectionPoolProperties.getMaintenanceFrequency(), TimeUnit.MILLISECONDS)
                .maintenanceInitialDelay(connectionPoolProperties.getMaintenanceInitialDelay(), TimeUnit.MILLISECONDS)
        );
    }
}
