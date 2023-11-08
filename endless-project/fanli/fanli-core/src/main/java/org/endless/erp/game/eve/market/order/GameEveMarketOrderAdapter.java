package org.endless.erp.game.eve.market.order;

import org.endless.erp.game.eve.share.adapter.esi.EsiAdapter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameEveMarketOrderAdapter
 *
 * @author Deng Haozhi
 * @date 2023/3/27 12:07
 * @since 0.0.2
 */
@Component
public class GameEveMarketOrderAdapter {

    private static final String SERVICE = "markets";

    private static final String SCENES = "orders";

    private final EsiAdapter esiAdapter;

    public GameEveMarketOrderAdapter(EsiAdapter esiAdapter) {
        this.esiAdapter = esiAdapter;
    }

    public HttpHeaders getHeader() {
        return esiAdapter.getHeader(SERVICE, SCENES);
    }

    public Integer getPages() {
        return esiAdapter.getPages(SERVICE, SCENES);
    }

    public List<?> getOrders(Integer page) {

        Map<String, String> paras = new HashMap<>();
        paras.put("datasource", "serenity");
        paras.put("order_type", "all");
        paras.put("page", page.toString());

        return esiAdapter.get(SERVICE, SCENES, paras);
    }

    public List<?> getOrders(String itemId) {

        Map<String, String> paras = new HashMap<>();
        paras.put("datasource", "serenity");
        paras.put("order_type", "all");
        paras.put("type_id", itemId);

        return esiAdapter.get(SERVICE, SCENES, paras);
    }
}
