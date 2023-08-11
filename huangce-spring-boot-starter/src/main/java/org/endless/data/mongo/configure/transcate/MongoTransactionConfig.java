package org.endless.data.mongo.configure.transcate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * MongoTransactionConfig
 *
 * <p>update 2023/07/20 16:24:48
 *
 * @author Deng Haozhi
 * @since 0.1.1
 */
@Configuration
public class MongoTransactionConfig {
    public @Bean MongoTransactionManager mongoTransactionManager(
            @Qualifier("mongoDatabaseFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}
