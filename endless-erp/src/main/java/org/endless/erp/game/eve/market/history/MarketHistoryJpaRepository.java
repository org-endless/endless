package org.endless.erp.game.eve.market.history;

import org.endless.erp.share.mongo.jpa.MongoJpaRepository;

/**
 * MarketHistoryJpaRepository
 *
 * @author Deng Haozhi
 * @date 2023/4/24 17:17
 * @since 0.0.2
 */
public interface MarketHistoryJpaRepository extends MongoJpaRepository<MarketHistory, String> {
}