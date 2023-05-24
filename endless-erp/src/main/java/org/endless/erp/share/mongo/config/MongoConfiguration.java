package org.endless.erp.share.mongo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

/**
 * erp mongo配置
 * create 2023/01/26 16:33
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */
@Configuration
public class MongoConfiguration {

    private final MongoProperties mongoProperties;

    public MongoConfiguration(MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    public @Bean MongoTemplate mongoTemplate(@Qualifier("mongoCustomConversions")  MongoCustomConversions mongoCustomConversions) {
        var mongoTemplate = new MongoTemplate(mongoDatabaseFactory(mongoProperties));
        var mappingMongoConverter = (MappingMongoConverter) mongoTemplate.getConverter();
        mappingMongoConverter.setCustomConversions(mongoCustomConversions);
        mappingMongoConverter.afterPropertiesSet();
        return mongoTemplate;
    }

    public @Bean MongoDatabaseFactory mongoDatabaseFactory(MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

    public @Bean MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(List.of(new FromDecimal128Converter(), new ToDecimal128Converter()));
    }
}