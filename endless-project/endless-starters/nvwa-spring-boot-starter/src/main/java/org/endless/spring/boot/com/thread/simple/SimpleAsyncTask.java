package org.endless.spring.boot.com.thread.simple;

import org.endless.spring.boot.com.thread.model.AsyncTask;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * SimpleAsyncTask
 *
 * @author Deng Haozhi
 * @date 2023/5/15 16:03
 * @since 0.0.3
 */
public interface SimpleAsyncTask extends AsyncTask {

    @Override
    @Async("asyncTaskExecutor")
    default <T> void run(T argument) {
        AsyncTask.super.run(argument);
    }

    @Override
    @Async("asyncTaskExecutor")
    default <T> void run(T[] arguments) {
        AsyncTask.super.run(arguments);
    }

    @Override
    @Async("asyncTaskExecutor")
    default <T> CompletableFuture<T> supply(T arguments) {
        return AsyncTask.super.supply(arguments);
    }

    @Override
    @Async("asyncTaskExecutor")
    default <T> CompletableFuture<T> supply(T[] arguments) {
        return AsyncTask.super.supply(arguments);
    }

}
