package org.endless.erp.game.eve.market.order;

import org.endless.erp.share.mongo.bulk.BulkMongoRepositoryImpl;
import org.endless.erp.share.util.date.DateFormatter;
import org.endless.erp.share.util.decimal.Decimal;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GameEvePriceService
 * update from ESI
 *
 * @author Deng Haozhi
 * @date 2023/3/16 19:44
 * @since 0.0.2
 */
@Service
public class GameEveMarketOrderService {

    private final GameEveMarketOrderAdapter gameEveMarketOrderAdapter;

    private final BulkMongoRepositoryImpl bulkMongoRepository;

    private final GameEveMarketOrderJpaRepository gameEveMarketOrderJpaRepository;

    private final MongoTemplate mongoTemplate;

    public GameEveMarketOrderService(BulkMongoRepositoryImpl bulkMongoRepository, GameEveMarketOrderAdapter gameEveMarketOrderAdapter, GameEveMarketOrderJpaRepository gameEveMarketOrderJpaRepository, MongoTemplate mongoTemplate) {
        this.bulkMongoRepository = bulkMongoRepository;
        this.gameEveMarketOrderAdapter = gameEveMarketOrderAdapter;
        this.gameEveMarketOrderJpaRepository = gameEveMarketOrderJpaRepository;
        this.mongoTemplate = mongoTemplate;
    }


    public void save() {

        Integer pages = gameEveMarketOrderAdapter.getPages();

        if (pages == 0) return;

        for (int i = 0; i < pages; i++) {

            var orderList = gameEveMarketOrderAdapter.getOrders(i + 1);
            List<Pair<Query, Update>> upsertList = new ArrayList<>();

            orderList.forEach(order -> {

                var rat = (Map<?, ?>) order;

                var query = Query.query(Criteria.where("orderId").is(String.valueOf(rat.get("order_id"))));
                var update = Update.update("itemId", rat.get("type_id"))
                        .set("isBuyOrder", rat.get("is_buy_order"))
                        .set("minVolume", rat.get("min_volume"))
                        .set("volumeRemain", rat.get("volume_remain"))
                        .set("volumeTotal", rat.get("volume_total"))
                        .set("price", Decimal.format(rat.get("price")))
                        .set("updateDateTime", DateFormatter.nowIso())
                        .set("updateTimeStamp", System.currentTimeMillis());

                upsertList.add(Pair.of(query, update));
            });
            bulkMongoRepository.upsert(upsertList, GameEveMarketOrder.class);
            System.out.println("upsert!");
        }
        System.out.println("GameEveMarketOrder save executed!");
    }

    // public Map<String, Object> getMarketPrice(List<String> itemIds) {
    //     var marketOrders = getByItemId(itemIds);
    //     return itemIds.stream().collect(Collectors.toMap(itemId -> itemId, itemId -> getPriceMap(marketOrders, itemId), (a, b) -> b));
    // }

    // public Map<String, Object> getMarketPrice(String itemId) {
    //
    //     Map<String, Object> priceMap = new HashMap<>();
    //     priceMap.put(itemId, getPriceMap(getByItemId(itemId), itemId));
    //     return priceMap;
    // }

    // protected Map<String, BigDecimal> getPriceMap(List<GameEveMarketOrder> gameEveMarketOrders, String itemId) {
    //
    //     Map<String, BigDecimal> priceMap = new HashMap<>();
    //     List<BigDecimal> curPriceList = new ArrayList<>();
    //     List<BigDecimal> buyPriceList = new ArrayList<>();
    //     gameEveMarketOrders.stream().filter(gameEveMarketOrder -> gameEveMarketOrder.getItemId().equals(itemId)).forEach(gameEveMarketOrder -> {
    //         if (gameEveMarketOrder.getIsBuyOrder().equals("true")) {
    //             buyPriceList.add(gameEveMarketOrder.getPrice());
    //             return;
    //         }
    //         curPriceList.add(gameEveMarketOrder.getPrice());
    //     });
    //     priceMap.put("curPrice", Decimal.min(curPriceList));
    //     priceMap.put("buyPrice", Decimal.max(buyPriceList));
    //     return priceMap;
    // }

    // public List<GameEveMarketOrder> getByItemId(String itemId) {
    //     return gameEveMarketOrderJpaRepository.findByItemId(itemId);
    // }

    public List<GameEveMarketOrder> getByItemId(List<String> itemIds) {
        Query query = new Query();
        query.addCriteria(Criteria.where("itemId").in(itemIds));
        return mongoTemplate.find(query, GameEveMarketOrder.class, "eve.market.order");
        // return marketOrderRepository.findByItemIdIn(itemIds);
    }
}