package org.endless.erp.game.eve.sales;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * GameEveSalesUpdateTaskImpl
 *
 * @author Deng Haozhi
 * @date 2023/5/9 15:33
 * @since 0.0.2
 */
@Log4j2
@Component
public class GameEveSalesUpdateTaskImpl implements GameEveSalesUpdateTask {
    //
    // private final BulkMongoRepository bulkMongoRepository;
    //
    // private final GameEveItemService gameEveItemService;
    //
    // private final GameEveMarketSaleHistoryService marketHistoryService;
    //
    // private final GameEveThreadPoolConfiguration gameEveThreadPoolConfiguration;
    //
    // public GameEveSalesUpdateTaskImpl(BulkMongoRepository bulkMongoRepository, GameEveItemService gameEveItemService, GameEveMarketSaleHistoryService marketHistoryService, GameEveThreadPoolConfiguration gameEveThreadPoolConfiguration) {
    //     this.bulkMongoRepository = bulkMongoRepository;
    //     this.gameEveItemService = gameEveItemService;
    //     this.marketHistoryService = marketHistoryService;
    //     this.gameEveThreadPoolConfiguration = gameEveThreadPoolConfiguration;
    // }
    //
    // @Async("eveTaskExecutor")
    // @Override
    // public void update(long pages) {
    //
    //     long begin = System.currentTimeMillis();
    //     log.debug("Thread: " + Thread.currentThread().getName() + " upsert begin: " + begin);
    //
    //     var threadName = Thread.currentThread().getName().split("-");
    //     var threadNumber = Integer.parseInt(threadName[threadName.length -1]);
    //     var corePoolSize = gameEveThreadPoolConfiguration.getCorePoolSize();
    //
    //     List<Pair<Query, Update>> upsertList = new ArrayList<>();
    //     for (int i = 0; i < pages; i++) {
    //         if (!((i + 1) % corePoolSize == threadNumber || ((i + 1) % corePoolSize == 0 && corePoolSize == threadNumber))) continue;
    //
    //         Pageable pageable = PageRequest.of(i, PAGE_SIZE / 10);
    //         var items = gameEveItemService.getItemsByPublished(true, pageable);
    //
    //         items.forEach(item -> {
    //             var query = Query.query(Criteria.where("itemId").is(item.getItemId()));
    //             var update = Update.update("sales.dailyMarketSalesByYear", Decimal.format128(getDailyMarketSalesByYear(item.getItemId())));
    //             upsertList.add(Pair.of(query, update));
    //         });
    //         log.debug("Thread: "+ Thread.currentThread().getName() +" updateSales page: " + i + " upsert!");
    //     }
    //     bulkMongoRepository.upsert(upsertList, PAGE_SIZE, GameEveItem.class);
    //
    //     long end = System.currentTimeMillis();
    //     log.debug("Thread: " + Thread.currentThread().getName() + " upsert end: " + end);
    //     log.info("Thread: "+ Thread.currentThread().getName() +" updateSales done!");
    // }
    //
    // /**
    //  *
    //  *
    //  * @param itemId itemID
    //  * @return java.math.BigDecimal
    //  **/
    // protected BigDecimal getDailyMarketSalesByYear(String itemId) {
    //     return Decimal.format(Decimal.divide(marketHistoryService.getSalesByItemId(itemId), new BigDecimal(365)));
    // }
}