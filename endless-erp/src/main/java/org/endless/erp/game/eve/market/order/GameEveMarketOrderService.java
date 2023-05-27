package org.endless.erp.game.eve.market.order;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.ddd.order.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * GameEvePriceService
 * <p>update from ESI
 * <p>create 2023/05/25 21:40
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Log4j2
@Service
public class GameEveMarketOrderService implements OrderService {

    private final GameEveMarketOrderAdapter gameEveMarketOrderAdapter;

    private final GameEveAsyncTask gameEveAsyncTask;

    private final MongoOperations mongoOperations;

    private final GameEveMarketOrderRepositoryBase gameEveMarketOrderJpaRepository;

    public GameEveMarketOrderService(
            GameEveMarketOrderAdapter gameEveMarketOrderAdapter,
            @Qualifier("gameEveMarketOrderLoadTask") GameEveAsyncTask gameEveAsyncTask,
            MongoOperations mongoOperations, GameEveMarketOrderRepositoryBase gameEveMarketOrderJpaRepository) {

        this.gameEveMarketOrderAdapter = gameEveMarketOrderAdapter;
        this.gameEveAsyncTask = gameEveAsyncTask;
        this.mongoOperations = mongoOperations;
        this.gameEveMarketOrderJpaRepository = gameEveMarketOrderJpaRepository;
    }

    @Override
    public void save() {

        var begin = System.currentTimeMillis();
        log.info("Loading!");
        log.debug("Load main thread begin: " + begin);

        Integer pages = gameEveMarketOrderAdapter.getPages();

        if (pages == 0) {
            log.error("Get order pages ERROR form the ESI!");
            return;
        }
        mongoOperations.updateMulti(
                Query.query(Criteria.where("existed").is(true)),
                Update.update("existed", false),
                GameEveMarketOrder.class);

        for (int i = 0; i < pages; i++) {
            log.trace("The page is " + (i + 1));
            gameEveAsyncTask.run(i + 1);
        }

        log.info("Saved executed!");
        log.debug("main thread cost : " + (System.currentTimeMillis() - begin));
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
}