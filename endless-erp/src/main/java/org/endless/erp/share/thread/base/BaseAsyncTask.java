package org.endless.erp.share.thread.base;

import org.endless.erp.share.thread.model.AsyncTask;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * BaseAsyncTask
 *
 * @author Deng Haozhi
 * @date 2023/5/15 16:03
 * @since 0.0.3
 */
public interface BaseAsyncTask extends AsyncTask {

    @Override
    @Async("baseTaskExecutor")
    default <T> void run(T argument) {
        AsyncTask.super.run(argument);
    }

    @Override
    @Async("baseTaskExecutor")
    default <T> void run(T[] arguments) {
        AsyncTask.super.run(arguments);
    }

    @Override
    @Async("baseTaskExecutor")
    default <T> CompletableFuture<T> supply(T arguments) {
        return AsyncTask.super.supply(arguments);
    }

    @Override
    @Async("baseTaskExecutor")
    default <T> CompletableFuture<T> supply(T[] arguments) {
        return AsyncTask.super.supply(arguments);
    }

    @Async("baseTaskExecutor")
    void run(List<Pair<Query, Update>> pairs, Class<?> entityClass);
}