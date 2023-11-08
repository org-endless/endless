package org.endless.spring.boot.data.mongo.bulk;

import org.endless.spring.boot.com.thread.model.AsyncTask;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * MongoBulkTask
 * <p>MongoDB批量更新插入操作异步任务
 * <p>MongoDB bulk upsert asynchronous task.
 * <p>
 * <p>create 2023/5/15 16:03
 * <p>update 2023/9/26 11:29
 *
 * @author Deng Haozhi
 * @see AsyncTask
 * @since 0.0.4
 */
public interface MongoBulkTask extends AsyncTask {

    @Override
    @Async("mongoTaskExecutor")
    default <T> void run(T argument) {
        AsyncTask.super.run(argument);
    }

    @Override
    @Async("mongoTaskExecutor")
    default <T> void run(T[] arguments) {
        AsyncTask.super.run(arguments);
    }

    @Override
    @Async("mongoTaskExecutor")
    default <T> CompletableFuture<T> supply(T arguments) {
        return AsyncTask.super.supply(arguments);
    }

    @Override
    @Async("mongoTaskExecutor")
    default <T> CompletableFuture<T> supply(T[] arguments) {
        return AsyncTask.super.supply(arguments);
    }

    /**
     * MongoDB批量更新插入操作异步任务
     * <p>Mongo DB bulk upsert asynchronous task.
     *
     * @param pairs       Query和Update类列表 (List of Query and Update.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    @Async("mongoTaskExecutor")
    void upsert(List<Pair<Query, Update>> pairs, Class<?> entityClass);

    /**
     * MongoDB批量删除操作异步任务
     * <p>Mongo DB bulk delete asynchronous task.
     *
     * @param queries     Query类列表 (List of Query class.)
     * @param entityClass Mongo文档实体类 (The entity class of Mongo document.)
     */
    @Async("mongoTaskExecutor")
    void remove(List<Query> queries, Class<?> entityClass);
}
