package org.endless.erp.game.eve.share.ddd.item;

import lombok.extern.log4j.Log4j2;
import org.endless.erp.game.eve.share.regular.GameEvePattern;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.ddd.item.ItemService;
import org.endless.erp.share.ddd.price.Price;
import org.endless.erp.share.util.file.FileLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.endless.erp.share.constant.ConstantResource.PAGE_SIZE;

/**
 * GameEveItemService
 *
 * @author Deng Haozhi
 * @date 2023/3/7 1:28
 * @since 0.0.2
 */
@Log4j2
@Service
public class GameEveItemService implements ItemService {

    private final @Qualifier("GameEveItemLoadTask") GameEveAsyncTask gameEveAsyncTask;

    private final GameEveItemJpaRepository gameEveItemJpaRepository;

    public GameEveItemService(@Qualifier("gameEveItemLoadTask") GameEveAsyncTask gameEveAsyncTask, GameEveItemJpaRepository gameEveItemJpaRepository) {
        this.gameEveAsyncTask = gameEveAsyncTask;
        this.gameEveItemJpaRepository = gameEveItemJpaRepository;
    }


    @Override
    public final <T> void load(T file) {

        var begin = System.currentTimeMillis();
        log.info("Loading!");
        log.debug("Load main thread begin: " + begin);

        var scanner = FileLoader.getScanner(String.valueOf(file), GameEvePattern.getFile());

        if (scanner == null) {
            log.error("Loaded failed!");
            return;
        }

        List<List<Map<String, String>>> scannerMaps = new ArrayList<>();
        scannerMaps.add(new ArrayList<>());
        var index = 0;

        while (scanner.hasNext()) {

            var itemId = scanner.findInLine(GameEvePattern.getFile()).replace(":", "").trim();
            var scannerMap = Map.of("itemId", itemId, "scanner", scanner.next());
            scannerMaps.get(index).add(scannerMap);

            if (scannerMaps.get(index).size() % PAGE_SIZE == 0) {

                log.debug("scannerMap size: " + scannerMaps.get(index).size() + "  scannerMap index: " + index);

                gameEveAsyncTask.run(scannerMaps.get(index));
                scannerMaps.add(new ArrayList<>());
                index++;
            }
        }
        gameEveAsyncTask.run(scannerMaps.get(index));

        log.info("GameEveItemService executed!");
        log.debug("main thread cost : " + (System.currentTimeMillis() - begin));
    }

    // public List<Map<String, String>> getTradingList() {
    //
    //     return getTypesByPublished(true).stream()
    //             .filter(type -> type.getProfit().getTradingProfitMargin() != null
    //                     && type.getProfit().getTradingProfitMargin().compareTo(new BigDecimal("10")) > 0
    //                     && type.getProfit().getTradingProfitMargin().compareTo(new BigDecimal("100")) < 0)
    //             .map(type -> {
    //                 Map<String, String> trading = new HashMap<>();
    //                 trading.put("name", type.getName().getZhName().getFullName());
    //                 trading.put("profit", type.getProfit().getTradingProfit().toString());
    //                 trading.put("profitMargin", type.getProfit().getTradingProfitMargin().toString());
    //                 return trading;
    //             })
    //             .sorted((m1, m2) -> new BigDecimal(m2.get("profitMargin")).compareTo(new BigDecimal(m1.get("profitMargin"))))
    //             .collect(Collectors.toList());
    // }

    // public List<Map<String, String>> getT1Manufacturing() {
    //     List<String> metaGroupIds = new ArrayList<>();
    //     var blueprints = blueprintService.getAll();
    //     metaGroupIds.add("1");
    //     return getTypesByManufacturing(metaGroupIds).stream()
    //             .filter(type ->
    //                     type.getProfit().getManufacturingProfitMargin() != null
    //                             && type.getProfit().getManufacturingProfitPerDay() != null
    //                             // && item.getProfit().getManufacturingProfitMargin().compareTo(new BigDecimal("10")) > 0
    //                             && type.getProfit().getManufacturingProfitPerDay().compareTo(new BigDecimal("10000000")) > 0)
    //             .filter(type -> getTypeByTypeId(blueprintService.getManufacturingBlueprint(blueprints, type.getTypeId()).getTypeId()).getMarketGroupId() != null)
    //             .map(type -> {
    //                 Map<String, String> manufacturing = new HashMap<>();
    //                 var profitPerday = type.getProfit().getManufacturingProfitPerDay();
    //                 var procurementDifference = CurrencyFormatter.format(CurrencyCalculation.subtract(profitPerday, type.getProfit().getManufacturingProcurementProfitPerDay()));
    //
    //                 manufacturing.put("typeId", type.getTypeId());
    //                 manufacturing.put("name", type.getName().getZhName().getFullName());
    //                 manufacturing.put("profit", type.getProfit().getManufacturingProfit().toString());
    //                 manufacturing.put("profitMargin", type.getProfit().getManufacturingProfitMargin().toString());
    //                 manufacturing.put("profitPerDay", profitPerday.toString());
    //                 if (procurementDifference != null)
    //                     manufacturing.put("procurementDifference", procurementDifference.toString());
    //                 manufacturing.put("isManufacturing", String.valueOf(type.getCost().getIsManufacturing()));
    //                 return manufacturing;
    //             })
    //             .sorted((m1, m2) -> new BigDecimal(m2.get("profitPerDay")).compareTo(new BigDecimal(m1.get("profitPerDay"))))
    //             .collect(Collectors.toList());
    // }

    public BigDecimal getCurPrice(String itemId) {
        var item = gameEveItemJpaRepository.findById(itemId).orElse(null);
        if (item == null) return null;
        return item.getPrice().getCurPrice();
    }

    public BigDecimal getCurPrice(List<GameEveItem> gameEveItems, String itemId) {
        return gameEveItems.stream().filter(item -> item.getItemId().equals(itemId)).map(GameEveItem::getPrice).map(Price::getCurPrice).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public Slice<GameEveItem> getSliceByPublished(Pageable pageable) {
        return gameEveItemJpaRepository.findSliceByPublished(pageable);
    }
    public GameEveItem getItemByItemId(String itemId) {
        return gameEveItemJpaRepository.findById(itemId).orElse(null);
    }
    //
    // public List<GameEveItem> getItemsByPublished(Boolean published) {
    //     return gameEveItemJpaRepository.findByPublished(published);
    // }
    //
    // public List<GameEveItem> getItemsByPublished(Boolean published, Pageable pageable) {
    //     return gameEveItemJpaRepository.findByPublished(published, pageable);
    // }
    //


    // public List<GameEveItem> getManufacturingItems(List<String> metaGroupIds) {
    //     return gameEveItemJpaRepository.findByPublishedAndMetaGroupIdIn(true, metaGroupIds);
    // }
    //
    // public Slice<GameEveItem> getManufacturingSlice(List<String> metaGroupIds, Pageable pageable) {
    //     return gameEveItemJpaRepository.findSliceByPublishedAndMetaGroupIdIn(true, metaGroupIds, pageable);
    // }

    // public List<GameEveItem> getItemsByItemIds(List<String> itemIds) {
    //     return gameEveItemJpaRepository.findByIdIn(itemIds);
    // }

    // public long getCountByPublished(Boolean published) {
    //     return gameEveItemJpaRepository.countByPublished(published);
    // }
}