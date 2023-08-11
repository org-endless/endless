package org.endless.com.thread.base;

import org.endless.com.thread.model.AsyncTask;
import org.springframework.scheduling.annotation.Async;

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

}
