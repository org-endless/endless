package org.endless.erp.share.ddd.formula;

/**
 * FormulaService
 * <p>配方服务接口
 * <p>The formula service interface.
 *
 * <p>create 2023/05/26 16:21
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
public interface FormulaService {

    default <T> void load(T argument) {
    }

    default <T> void load(T[] arguments) {
    }

    default <T, E extends Enum<E>> void load(T tArgument, Enum<E> tEnum) {
    }

    default <T, E extends Enum<E>> void load(T[] tArguments, Enum<E> tEnum) {
    }
}
