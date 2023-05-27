package org.endless.erp.share.mongo.bulk;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.thread.base.BaseAsyncTask;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * BulkMongoUpsertTask
 * <p>create 2023/05/25 21:51
 *
 * @author Deng Haozhi
 * @see BaseAsyncTask
 * @since 0.0.3
 */
@Log4j2
@Component("BulkMongoTask")
public class BulkMongoUpsertTask implements BaseAsyncTask {

    private final MongoOperations mongoOperations;

    public BulkMongoUpsertTask(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void run(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        long begin = System.currentTimeMillis();
        log.debug("Upsert task running!");
        log.debug("Thread: " + Thread.currentThread().getName() + " upsert begin: " + begin);

        var bulkOps = mongoOperations.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.upsert(pairs);
            var result = bulkOps.execute();

            long end = System.currentTimeMillis();
            log.debug("Upsert task executed! The result is: " + result);
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert end: " + end);
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert completed!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}