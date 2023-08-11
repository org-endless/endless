package org.endless.erp.game.eve.market.saleHistory;

import org.endless.data.mongo.base.BaseMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * GameEveMarketSaleHistoryRepository
 *
 * <p>create 2023/05/26 16:16
 *
 * @author Deng Haozhi
 * @see BaseMongoRepository
 * @since 0.0.3
 */
@Repository
public interface GameEveMarketSaleHistoryRepository extends BaseMongoRepository<GameEveMarketSaleHistory, String> {
}
