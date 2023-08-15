package org.endless.erp.game.eve.share.thread;

import org.endless.spring.boot.com.thread.model.AsyncTask;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * GameEveAsyncTask
 *
 * @author Deng Haozhi
 * @date 2023/5/15 16:01
 * @since 0.0.3
 */
public interface GameEveAsyncTask extends AsyncTask {

    @Override
    @Async("eveTaskExecutor")
    default <T> void run(T argument) {
        AsyncTask.super.run(argument);
    }

    @Override
    @Async("eveTaskExecutor")
    default <T> void run(T[] arguments) {
        AsyncTask.super.run(arguments);
    }

    @Override
    @Async("eveTaskExecutor")
    default <T, E extends Enum<E>> void run(T tArgument, Enum<E> tEnum) {
        AsyncTask.super.run(tArgument, tEnum);
    }

    @Override
    @Async("eveTaskExecutor")
    default <T, E extends Enum<E>> void run(T[] tArguments, Enum<E> tEnum) {
        AsyncTask.super.run(tArguments, tEnum);
    }

    @Override
    @Async("eveTaskExecutor")
    default <T> CompletableFuture<T> supply(T arguments) {
        return AsyncTask.super.supply(arguments);
    }

    @Override
    @Async("eveTaskExecutor")
    default <T> CompletableFuture<T> supply(T[] arguments) {
        return AsyncTask.super.supply(arguments);
    }
}
