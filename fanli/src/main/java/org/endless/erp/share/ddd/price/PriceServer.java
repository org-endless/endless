package org.endless.erp.share.ddd.price;

/**
 * PriceServer
 *
 * <p>价格服务接口
 *
 * <p>The price service interface.
 *
 * <p>create 2023/05/26 15:41
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
public interface PriceServer {

  default void update() {}

  default <T> void update(T argument) {}

  default <T> void update(T[] arguments) {}
}
