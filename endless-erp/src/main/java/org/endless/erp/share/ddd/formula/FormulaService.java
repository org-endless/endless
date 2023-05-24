package org.endless.erp.share.ddd.formula;

/**
 * FormulaService
 * <p>配方服务
 * <p>The Formula Service for ERP.
 *
 * @author Deng Haozhi
 * @date 2023/5/16 16:40
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