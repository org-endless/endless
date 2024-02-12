package org.endless.spring.boot.data.mongo.bulk;

import lombok.extern.slf4j.Slf4j;
import org.endless.spring.boot.common.utility.list.ListSplitter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * MongoBulkTemplate
 * <p>BulkMongoRepository实现类，定义一些批量操作MongoDB的方法
 * <p>The MongoBulkOperations implements class. Define some methods for bulk operations on Mongo DB.
 * <p>
 * <p>create 2023/05/25 21:52
 * <p>update 2023/9/26 11:29
 *
 * @author Deng Haozhi
 * @see MongoBulkOperations
 * @since 0.0.4
 */
@Slf4j
public class MongoBulkTemplate implements MongoBulkOperations {

    private final MongoBulkHandler bulkHandler;
    private final MongoBulkTask bulkTask;

    public MongoBulkTemplate(MongoBulkHandler bulkHandler, MongoBulkTask bulkTask) {
        this.bulkHandler = bulkHandler;
        this.bulkTask = bulkTask;
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        bulkHandler.upsert(pairs, entityClass);
    }

    @Override
    public void upsert(List<Pair<Query, Update>> pairs, Integer pageSize, Class<?> entityClass) {

        var pages = ListSplitter.sub(pairs, pageSize);

        pages.forEach(
                page -> {
                    bulkTask.upsert(page, entityClass);
                });
    }

    @Override
    public void remove(List<Query> queries, Class<?> entityClass) {
        bulkHandler.remove(queries, entityClass);
    }

    @Override
    public void remove(List<Query> queries, Integer pageSize, Class<?> entityClass) {
        var pages = ListSplitter.sub(queries, pageSize);

        pages.forEach(
                page -> {
                    bulkTask.remove(page, entityClass);
                });
    }
}
