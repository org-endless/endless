package org.endless.erp.game.eve.market.order;

import org.endless.data.mongo.base.BaseMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * GameEveMarketOrderRepository
 *
 * @author Deng Haozhi
 * @date 2023/4/10 19:36
 * @since 0.0.2
 */
@Repository
public interface GameEveMarketOrderRepository extends BaseMongoRepository<GameEveMarketOrder, String> {

    // List<GameEveMarketOrder> findByItemId(String typeId);
    //
    // List<GameEveMarketOrder> findMarketOrderByPriceLessThan(BigDecimal price);
    //
    // List<GameEveMarketOrder> findMarketOrderByPriceGreaterThan(BigDecimal price);
    //
    // List<GameEveMarketOrder> findByTypeIdIn(List<String> typeIds);
}
