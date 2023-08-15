package org.endless.spring.boot.data.mongo.bulk;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * BulkMongoConfiguration
 * <p>
 * <p>
 *
 * <p>create 2023/8/14 18:53
 * <p>update 2023/8/14 18:53
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class BulkMongoConfiguration {

    private final MongoOperations mongoOperations;

    public BulkMongoConfiguration(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public @Bean BulkMongoTemplate bulkMongoTemplate(
            @Qualifier("bulkMongoHandler") BulkMongoHandler mongoHandler,
            @Qualifier("bulkMongoUpsertTask") BulkMongoUpsertTask upsertTask) {
        return new BulkMongoTemplate(mongoHandler, upsertTask);
    }

    public @Bean BulkMongoUpsertTask bulkMongoUpsertTask(
            @Qualifier("bulkMongoHandler") BulkMongoHandler bulkMongoHandler) {
        return new BulkMongoUpsertTaskImpl(bulkMongoHandler);
    }

    public @Bean BulkMongoHandler bulkMongoHandler() {
        return new BulkMongoHandler(mongoOperations);
    }
}
