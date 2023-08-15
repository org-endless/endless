package org.endless.spring.boot.data.mongo.bulk;

import lombok.extern.slf4j.Slf4j;
import org.endless.spring.boot.com.utiliy.list.ListSplitter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * BulkMongoTemplate
 * <p>BulkMongoRepository实现类，定义一些批量操作MongoDB的方法
 * <p>The BulkMongoOperations implements class. Define some methods for bulk operations on Mongo DB.
 * <p>
 * <p>create 2023/05/25 21:52
 * <p>update 2023/05/27 19:52
 *
 * @author Deng Haozhi
 * @see BulkMongoOperations
 * @since 0.0.3
 */
@Slf4j
public class BulkMongoTemplate implements BulkMongoOperations {

    private final BulkMongoHandler bulkMongoHandler;

    private final BulkMongoUpsertTask upsertTask;

    public BulkMongoTemplate(BulkMongoHandler bulkMongoHandler, BulkMongoUpsertTask upsertTask) {
        this.bulkMongoHandler = bulkMongoHandler;
        this.upsertTask = upsertTask;
    }

    /**
     * 单线程批量更新插入
     *
     * <p>Single-threaded bulk upsert
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        bulkMongoHandler.upsert(pairs, entityClass);
    }

    /**
     * 多线程批量更新插入
     *
     * <p>Multi-threaded bulk upsert.
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param pageSize    每线程处理的页面大小 (Page size per thread.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Integer pageSize, Class<?> entityClass) {

        var pages = ListSplitter.sub(pairs, pageSize);

        pages.forEach(
                page -> {
                    upsertTask.run(page, entityClass);
                });
    }
}
