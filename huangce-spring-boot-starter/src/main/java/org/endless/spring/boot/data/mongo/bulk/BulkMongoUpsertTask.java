package org.endless.spring.boot.data.mongo.bulk;

import org.endless.spring.boot.com.thread.base.BaseAsyncTask;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * BulkMongoUpsertTask
 * <p>MongoDB批量更新插入操作异步任务
 * <p>MongoDB bulk upsert asynchronous task.
 * <p>
 * <p>create 2023/05/25 21:51
 * <p>update 2023/05/27 19:50
 *
 * @author Deng Haozhi
 * @see BaseAsyncTask
 * @since 0.0.3
 */

public interface BulkMongoUpsertTask extends BaseAsyncTask {

    /**
     * 运行MongoDB批量更新插入操作异步任务
     *
     * <p>Run the Mongo DB bulk upsert asynchronous task.
     *
     * @param pairs       定义Query和Update的List (Define a List of Query and Update.)
     * @param entityClass 进行操作的实体类 (The entity class that operates.)
     */
    @Async("baseTaskExecutor")
    default void run(List<Pair<Query, Update>> pairs, Class<?> entityClass) {
    }
}
