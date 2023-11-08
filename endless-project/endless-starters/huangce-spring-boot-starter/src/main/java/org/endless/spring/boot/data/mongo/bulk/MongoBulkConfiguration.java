package org.endless.spring.boot.data.mongo.bulk;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * MongoBulkConfiguration
 * <p> Mongo 批量操作配置类
 * <p> Mongo bulk operation configuration class.
 * <p>
 * <p>create 2023/8/14 18:53
 * <p>update 2023/9/26 11:27
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
public class MongoBulkConfiguration {

    private final MongoOperations operations;

    public MongoBulkConfiguration(MongoOperations operations) {
        this.operations = operations;
    }

    @ConditionalOnMissingBean(MongoBulkOperations.class)
    public @Bean MongoBulkTemplate mongoBulkTemplate(
            @Qualifier("mongoBulkHandler") MongoBulkHandler bulkHandler,
            @Qualifier("mongoBulkTask") MongoBulkTask bulkTask) {
        return new MongoBulkTemplate(bulkHandler, bulkTask);
    }

    @ConditionalOnMissingBean(MongoBulkTask.class)
    public @Bean MongoBulkTask mongoBulkTask(
            @Qualifier("mongoBulkHandler") MongoBulkHandler bulkHandler) {
        return new SimpleMongoBulkTask(bulkHandler);
    }

    @ConditionalOnMissingBean(MongoBulkHandler.class)
    public @Bean MongoBulkHandler mongoBulkHandler() {
        return new SimpleMongoBulkHandler(operations);
    }
}
