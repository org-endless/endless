package org.endless.erp.share.thread.model;

import java.util.concurrent.CompletableFuture;

/**
 * AsyncTask
 *
 * @author Deng Haozhi
 * @date 2023/5/15 15:12
 * @since 0.0.3
 */
public interface AsyncTask {

    default <T> void run(T argument) {
    }

    default <T> void run(T[] arguments) {
    }

    default <T, E extends Enum<E>> void run(T tArgument, Enum<E> tEnum) {
    }

    default <T, E extends Enum<E>> void run(T[] tArguments, Enum<E> tEnum) {
    }

    default <T> CompletableFuture<T> supply(T arguments) {
        return null;
    }

    default <T> CompletableFuture<T> supply(T[] arguments) {
        return null;
    }
}