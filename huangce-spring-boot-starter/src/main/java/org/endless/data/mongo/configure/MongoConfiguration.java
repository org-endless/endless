package org.endless.data.mongo.configure;

import org.endless.data.mongo.configure.decimal128.FromDecimal128Converter;
import org.endless.data.mongo.configure.decimal128.ToDecimal128Converter;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

/**
 * erp mongo配置 create 2023/01/26 16:33
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

    public @Bean MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

    public @Bean MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(
                List.of(new FromDecimal128Converter(), new ToDecimal128Converter()));
    }
}
