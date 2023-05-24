package org.endless.erp.game.eve.market.history;

import org.endless.erp.game.eve.share.adapter.esi.EsiAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MarketHistoryAdapter
 *
 * @author Deng Haozhi
 * @date 2023/4/23 16:39
 * @since 0.0.2
 */
@Component
public class MarketHistoryAdapter {

    private final EsiAdapter esiAdapter;

    public MarketHistoryAdapter(EsiAdapter esiAdapter) {
        this.esiAdapter = esiAdapter;
    }

    public List<?> getHistory(String typeId){

        Map<String, String> paras = new HashMap<>();
        paras.put("datasource", "serenity");
        paras.put("type_id", typeId);

        return esiAdapter.get("markets", "history", paras);
    }
}