package org.endless.erp.share.mongo.bulk;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.thread.base.BaseAsyncTask;
import org.endless.erp.share.util.list.ListSplitter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * BulkMongoRepositoryImpl
 *
 * @author Deng Haozhi
 * @date 2023/4/8 16:32
 * @since 0.0.2
 */
@Log4j2
@Component
public class BulkMongoRepositoryImpl implements BulkMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Qualifier("BulkMongoTask")
    private final BaseAsyncTask baseAsyncTask;

    public BulkMongoRepositoryImpl(MongoTemplate mongoTemplate, BaseAsyncTask baseAsyncTask) {
        this.mongoTemplate = mongoTemplate;
        this.baseAsyncTask = baseAsyncTask;
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairList, Class<?> entityClass) {

        long begin = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " upsert begin: " + begin);

        var bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.upsert(pairList);
            bulkOps.execute();

            long end = System.currentTimeMillis();
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert end: " + end);
            log.debug("Thread: " + Thread.currentThread().getName() + " upsert completed!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairList, Integer bulkSize, Class<?> entityClass) {

        var upsertLists = ListSplitter.sub(pairList, bulkSize);

        upsertLists.forEach(upsertList -> {
            baseAsyncTask.run(upsertList, entityClass);
        });
    }
}