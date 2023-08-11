package org.endless.erp.game.eve.price;

import org.endless.erp.share.ddd.price.PriceServer;
import org.springframework.stereotype.Service;

/**
 * GameEvePriceService
 *
 * @author Deng Haozhi
 * @date 2023/4/28 21:42
 * @since 0.0.2
 */
@Service("gameEvePriceService")
public class GameEvePriceService implements PriceServer {

    // private final BulkMongoRepository bulkMongoRepository;
    //
    // private final GameEveItemService gameEveItemService;
    //
    // private final GameEveMarketOrderService gameEveMarketOrderService;
    //
    // public GameEvePriceService(BulkMongoRepository bulkMongoRepository, GameEveItemService gameEveItemService, GameEveMarketOrderService gameEveMarketOrderService) {
    //     this.bulkMongoRepository = bulkMongoRepository;
    //     this.gameEveItemService = gameEveItemService;
    //     this.gameEveMarketOrderService = gameEveMarketOrderService;
    // }

    // @Override
    // public void update() {
    //
    //     Pageable pageable = PageRequest.of(0, PAGE_SIZE);
    //     List<Pair<Query, Update>> upsertList = new ArrayList<>();
    //
    //     while (true) {
    //
    //         var slice = gameEveItemService.getSliceByPublished(pageable);
    //         var items = slice.getContent();
    //         var itemId = items.stream().map(GameEveItem::getItemId).toList();
    //
    //         var marketPrice = gameEveMarketOrderService.getMarketPrice(itemId);
    //
    //         items.forEach(item -> marketPrice.keySet().stream()
    //                 .filter(marketKey -> marketKey.equals(item.getItemId()))
    //                 .forEach(marketKey -> {
    //                     var query = Query.query(Criteria.where("itemId").is(item.getItemId()));
    //                     var update = Update.update("price.curPrice", Decimal.format128(((Map<?, ?>) marketPrice.get(marketKey)).get("curPrice")))
    //                             .set("price.buyPrice", Decimal.format128(((Map<?, ?>) marketPrice.get(marketKey)).get("buyPrice")));
    //                     upsertList.add(Pair.of(query, update));
    //                 }));
    //
    //         if (!slice.hasNext()) break;
    //         pageable = slice.nextPageable();
    //     }
    //     bulkMongoRepository.upsert(upsertList, 1000, GameEveItem.class);
    //     System.out.println("GameEveItemService updatePrice executed!");
    // }
}
