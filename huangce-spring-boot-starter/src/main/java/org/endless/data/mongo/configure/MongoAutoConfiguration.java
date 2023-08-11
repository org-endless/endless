package org.endless.data.mongo.configure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

/**
 * MongoAutoConfiguration
 * <p>
 * <p>
 *
 * <p>create 2023/8/12 2:06
 * <p>update 2023/8/12 2:06
 *
 * @author Deng Haozhi
 * @since 0.1.1
 */
@AutoConfiguration
public class MongoAutoConfiguration {
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
}
