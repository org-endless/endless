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
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BulkMongoRepositoryImpl
 * <p>BulkMongoRepository实现类，定义一些批量操作MongoDB的方法
 * <p>The BulkMongoRepository implements class. Define some methods for bulk operations on Mongo DB.
 * <p>
 * <p>create 2023/05/25 21:52
 * <p>update 2023/05/27 19:52
 *
 * @author Deng Haozhi
 * @see BulkMongoRepository
 * @since 0.0.3
 */
@Log4j2
@Repository
public class BulkMongoRepositoryImpl implements BulkMongoRepository {

    private final MongoOperations mongoOperations;

    private final BaseAsyncTask baseAsyncTask;

    public BulkMongoRepositoryImpl(
            MongoOperations mongoOperations,
            @Qualifier("BulkMongoTask") BaseAsyncTask baseAsyncTask) {
        this.mongoOperations = mongoOperations;
        this.baseAsyncTask = baseAsyncTask;
    }

    /**
     * 单线程批量更新插入
     * <p>Single-threaded bulk upsert
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass) {

        log.debug("Thread: " + Thread.currentThread().getName() + " upsert for >" + entityClass.getName() + "< executing");

        var bulkOps = mongoOperations.bulkOps(BulkOperations.BulkMode.UNORDERED, entityClass);

        try {
            bulkOps.upsert(pairs);
            var result = bulkOps.execute();

            log.debug("Thread: " + Thread.currentThread().getName() + " upsert for >" + entityClass.getName() + "< executed!");
            log.trace("The result is: " + result);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 多线程批量更新插入
     * <p>Multi-threaded bulk upsert.
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param pageSize    每线程处理的页面大小 (Page size per thread.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Integer pageSize, Class<?> entityClass) {

        var pages = ListSplitter.sub(pairs, pageSize);

        pages.forEach(page -> {
            baseAsyncTask.run(page, entityClass);
        });
    }
}