package org.endless.erp.game.eve.market.order;

import org.endless.erp.share.mongo.jpa.MongoJpaRepository;

import java.util.List;

/**
 * GameEveMarketOrderJpaRepository
 *
 * @author Deng Haozhi
 * @date 2023/4/10 19:36
 * @since 0.0.2
 */
public interface GameEveMarketOrderJpaRepository extends MongoJpaRepository<GameEveMarketOrder, String> {

    // List<GameEveMarketOrder> findByItemId(String typeId);
    //
    // List<GameEveMarketOrder> findMarketOrderByPriceLessThan(BigDecimal price);
    //
    // List<GameEveMarketOrder> findMarketOrderByPriceGreaterThan(BigDecimal price);
    //
    // List<GameEveMarketOrder> findByTypeIdIn(List<String> typeIds);
}