package org.endless.erp.game.eve.market.saleHistory;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.mongo.bulk.BulkMongoRepositoryImpl;
import org.endless.erp.share.util.date.DateFormatter;
import org.endless.erp.share.util.decimal.Decimal;
import org.endless.erp.share.util.object.ObjectToMongoObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private final GameEveMarketSaleHistoryAdapter gameEveMarketSaleHistoryAdapter;

    private final BulkMongoRepositoryImpl bulkMongoRepository;

    public GameEveMarketSaleHistorySaveTask(GameEveMarketSaleHistoryAdapter gameEveMarketSaleHistoryAdapter, BulkMongoRepositoryImpl bulkMongoRepository) {
        this.gameEveMarketSaleHistoryAdapter = gameEveMarketSaleHistoryAdapter;
        this.bulkMongoRepository = bulkMongoRepository;
    }

    @Override
    public <T> void run(T itemId) {

        long begin = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " saving begin: " + begin);

        var historyList = gameEveMarketSaleHistoryAdapter.getHistory(String.valueOf(itemId));

        if (historyList == null || historyList.isEmpty()) {
            log.debug("Get NOTHING from the itemId " + itemId);
            return;
        }

        List<Pair<Query, Update>> pairs = new ArrayList<>();
        var industryId = "game.eve";

        historyList.forEach(history -> {

            var rat = (Map<?, ?>) ObjectToMongoObject.convert(history);
            var date = rat.get("date");

            var id = industryId + "_" + date + "_" + itemId;
            var minPrice = Decimal.format(rat.get("lowest"));
            var maxPrice = Decimal.format(rat.get("highest"));
            var averagePrice = Decimal.average(List.of(minPrice, maxPrice));

            var query = Query.query(Criteria.where("id").is(id));
            var update = Update.update("itemId", itemId)
                    .set("industryId", industryId)
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
        bulkMongoRepository.upsert(pairs, GameEveMarketSaleHistory.class);

        long end = System.currentTimeMillis();
        log.debug("Thread: " + Thread.currentThread().getName() + " saving end: " + end);
        log.info("Thread: " + Thread.currentThread().getName() + " saved completed!");
    }
}