package org.endless.erp.game.eve.market.history;

import org.endless.erp.share.util.decimal.Decimal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * MarketHistoryService
 *
 * @author Deng Haozhi
 * @date 2023/4/23 16:37
 * @since 0.0.2
 */
@Service
public class MarketHistoryService {

    private final MarketHistoryAdapter marketHistoryAdapter;

    public MarketHistoryService(MarketHistoryAdapter marketHistoryAdapter) {
        this.marketHistoryAdapter = marketHistoryAdapter;
    }

    public BigDecimal getSalesByItemId(String itemId) {

        try {

            return marketHistoryAdapter.getHistory(itemId).stream().map(map -> Decimal.format(String.valueOf(((Map<?, ?>) map).get("volume")))).reduce(BigDecimal::add).orElse(null);

        } catch (NullPointerException e) {

            return null;
        }
    }
}