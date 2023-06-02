package org.endless.erp.game.eve.market.saleHistory;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.game.eve.item.GameEveItem;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.mongo.bulk.BulkMongoRepository;
import org.endless.erp.share.util.date.DateFormatter;
import org.endless.erp.share.util.decimal.Decimal;
import org.endless.erp.share.util.object.ObjectToMongoObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.endless.erp.share.ddd.industry.Industry.GAME_EVE;

/**
 * GameEveMarketSaleHistorySaveTask
 * <p>
 * <p>
 *
 * <p>create 2023/5/26 17:21
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Log4j2
@Component("gameEveMarketSaleHistoryTask")
public class GameEveMarketSaleHistorySaveTask implements GameEveAsyncTask {

    private final GameEveMarketSaleHistoryAdapter marketSaleHistoryAdapter;

    private final BulkMongoRepository bulkRepository;

    public GameEveMarketSaleHistorySaveTask(GameEveMarketSaleHistoryAdapter marketSaleHistoryAdapter, BulkMongoRepository bulkRepository) {
        this.marketSaleHistoryAdapter = marketSaleHistoryAdapter;
        this.bulkRepository = bulkRepository;
    }


    @Override
    public <T> void run(T items) {

        log.debug("Thread: " + Thread.currentThread().getName() + " save executing");

        if (CollectionUtils.isEmpty((List<?>) items)) {
            log.error("This page has no itemIds!");
            return;
        }
        ((List<?>) items).forEach(item -> {
            var itemId = ((GameEveItem) item).getItemId();
            var historyList = marketSaleHistoryAdapter.getHistory(itemId);
            if (CollectionUtils.isEmpty(historyList)) {
                log.debug("The itemId: " + itemId + " get no data from ESI!");
                return;
            }
            List<Pair<Query, Update>> pairs = new ArrayList<>();

            historyList.forEach(history -> {
                var rat = (Map<?, ?>) ObjectToMongoObject.convert(history);
                var date = rat.get("date");
                var id = GAME_EVE + "_" + date + "_" + itemId;
                var minPrice = Decimal.format(rat.get("lowest"));
                var maxPrice = Decimal.format(rat.get("highest"));
                var averagePrice = Decimal.average(List.of(minPrice, maxPrice));

                var query = Query.query(Criteria.where("id").is(id));
                var update = Update.update("itemId", itemId)
                        .set("industryId", GAME_EVE)
                        .set("date", date)
                        .set("price.minPrice", minPrice)
                        .set("price.maxPrice", maxPrice)
                        .set("price.averagePrice", averagePrice)
                        .set("totalQuantity", Decimal.format(rat.get("volume")))
                        .set("orderQuantity", Decimal.format(rat.get("order_count")))
                        .set("updateDateTime", DateFormatter.nowIso())
                        .set("updateTimeStamp", System.currentTimeMillis());
                pairs.add(Pair.of(query, update));
            });
            bulkRepository.upsert(pairs, GameEveMarketSaleHistory.class);
        });
        log.info("Thread: " + Thread.currentThread().getName() + " save executed completely!");
    }
}