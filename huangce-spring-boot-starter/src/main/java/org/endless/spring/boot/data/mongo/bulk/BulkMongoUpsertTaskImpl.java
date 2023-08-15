package org.endless.spring.boot.data.mongo.bulk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;

import java.util.List;

/**
 * BulkMongoUpsertTaskImpl
 * <p>MongoDB批量更新插入操作异步任务
 * <p>MongoDB bulk upsert asynchronous task.
 * <p>
 * <p>create 2023/8/10 19:50
 * <p>update 2023/8/10 19:50
 *
 * @author Deng Haozhi
 * @since 0.0.4
 */
@Slf4j
public class BulkMongoUpsertTaskImpl implements BulkMongoUpsertTask {

    private final BulkMongoHandler bulkMongoHandler;

    public BulkMongoUpsertTaskImpl(BulkMongoHandler bulkMongoHandler) {
        this.bulkMongoHandler = bulkMongoHandler;
    }

    /**
     * 运行MongoDB批量更新插入操作异步任务
     *
     * <p>Run the Mongo DB bulk upsert asynchronous task.
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    public void run(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
        bulkMongoHandler.upsert(pairs, entityClass);
    }
}
