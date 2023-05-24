package org.endless.erp.share.mongo.bulk;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.thread.base.BaseAsyncTask;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Deng Haozhi
 * @date 2023/5/10 23:52
 * @since 0.0.2
 */
@Log4j2
@Component("BulkMongoTask")
public class BulkMongoTask implements BaseAsyncTask {

    private final MongoTemplate mongoTemplate;

    public BulkMongoTask(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        long begin = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " upsert begin: " + begin);

        var bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.upsert(pairs);
            bulkOps.execute();

            long end = System.currentTimeMillis();
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert end: " + end);
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert completed!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}