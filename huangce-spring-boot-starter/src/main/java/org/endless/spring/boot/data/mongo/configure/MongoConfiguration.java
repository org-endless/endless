package org.endless.spring.boot.data.mongo.configure;

import lombok.extern.slf4j.Slf4j;
import org.endless.spring.boot.data.mongo.configure.decimal128.FromDecimal128Converter;
import org.endless.spring.boot.data.mongo.configure.decimal128.ToDecimal128Converter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

/**
 * MongoConfiguration
 * <p>create 2023/01/26 16:33
 * <p>update 2023/8/15 16:01
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
// @EnableConfigurationProperties(CustomMongoProperties.class)
public class MongoConfiguration {

    private final MongoProperties mongoProperties;

    public MongoConfiguration(
            MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
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

    public @Bean MongoTransactionManager mongoTransactionManager(
            @Qualifier("mongoDatabaseFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }

    public @Bean MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

    public @Bean MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(
                List.of(new FromDecimal128Converter(), new ToDecimal128Converter()));
    }
}
