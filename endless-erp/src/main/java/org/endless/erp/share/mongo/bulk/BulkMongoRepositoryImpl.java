package org.endless.erp.share.mongo.bulk;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.thread.base.BaseAsyncTask;
import org.endless.erp.share.util.list.ListSplitter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * BulkMongoRepositoryImpl
 * <p>create 2023/05/25 21:52
 *
 * @author Deng Haozhi
 * @see BulkMongoRepository
 * @since 0.0.3
 */
@Log4j2
@Component
public class BulkMongoRepositoryImpl implements BulkMongoRepository {

    private final MongoOperations mongoOperations;

    @Qualifier("BulkMongoTask")
    private final BaseAsyncTask baseAsyncTask;

    public BulkMongoRepositoryImpl(MongoOperations mongoOperations, BaseAsyncTask baseAsyncTask) {
        this.mongoOperations = mongoOperations;
        this.baseAsyncTask = baseAsyncTask;
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass) {

        long begin = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " upsert begin: " + begin);

        var bulkOps = mongoOperations.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.upsert(pairs);
            bulkOps.execute();

            long end = System.currentTimeMillis();
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert end: " + end);
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert completed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Integer pageSize, Class<?> entityClass) {

        var upsertLists = ListSplitter.sub(pairs, pageSize);

        upsertLists.forEach(upsertList -> {
            baseAsyncTask.run(upsertList, entityClass);
        });
    }
}