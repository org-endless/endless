package org.endless.erp.share.ddd.saleHistory;

/**
 * SaleHistoryService
 *
 * <p>销售历史服务接口
 *
 * <p>The service interface of sale saleHistory.
 *
 * <p>create 2023/5/26 16:16
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
public interface SaleHistoryService {

  default void save() {}

  default <T> void save(T argument) {}

  default <T> void save(T[] arguments) {}
}
