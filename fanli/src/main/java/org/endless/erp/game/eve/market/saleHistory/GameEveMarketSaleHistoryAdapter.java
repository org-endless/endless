package org.endless.erp.game.eve.market.saleHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.endless.erp.game.eve.share.adapter.esi.EsiAdapter;
import org.springframework.stereotype.Component;

/**
 * GameEveMarketSaleHistoryAdapter
 * <p>EVE游戏中市场销售历史查询接口适配器，由ESI提供
 * <p>The adapter for ESI market sale history query interface.
 *
 * <p>create 2023/05/26 16:15
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Component
public class GameEveMarketSaleHistoryAdapter {

    private final EsiAdapter esiAdapter;

    public GameEveMarketSaleHistoryAdapter(EsiAdapter esiAdapter) {
        this.esiAdapter = esiAdapter;
    }

    public List<?> getHistory(String itemId) {

        Map<String, String> paras = new HashMap<>();
        paras.put("datasource", "serenity");
        paras.put("type_id", itemId);

        return esiAdapter.get("markets", "history", paras);
    }
}
