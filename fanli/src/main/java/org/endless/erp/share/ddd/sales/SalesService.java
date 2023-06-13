package org.endless.erp.share.ddd.sales;

/**
 * SalesService
 *
 * <p>销量服务接口
 *
 * <p>The sales service interface.
 *
 * <p>create 2023/5/26 16:50
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
public interface SalesService {

  default void update() {}

  default <T> void update(T argument) {}

  default <T> void update(T[] arguments) {}
}
