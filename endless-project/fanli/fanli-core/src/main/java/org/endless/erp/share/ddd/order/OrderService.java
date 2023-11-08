package org.endless.erp.share.ddd.order;

/**
 * OrderService
 * <p>订单服务接口
 * <p>The order service interface.
 *
 * <p>create 2023/5/25 21:41
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
public interface OrderService {

    default void save() {
    }

    default <T> void save(T argument) {
    }

    default <T> void save(T[] arguments) {
    }
}
