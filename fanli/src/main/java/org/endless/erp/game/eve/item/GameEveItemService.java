package org.endless.erp.game.eve.item;

import lombok.extern.slf4j.Slf4j;
import org.endless.erp.game.eve.share.regular.GameEvePattern;
import org.endless.erp.game.eve.share.thread.GameEveAsyncTask;
import org.endless.erp.share.ddd.item.ItemService;
import org.endless.spring.boot.com.utiliy.file.FileLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.endless.erp.share.constant.ConstantResource.PAGE_SIZE;


/**
 * GameEveItemService
 * <p>游戏EVE物品/商品服务类
 * <p>Game EVE item services.
 * <p>
 * <p>create 2023/03/07 1:28
 * <p>update 2023/05/28 00:31
 *
 * @author Deng Haozhi
 * @see ItemService
 * @since 0.0.3
 */
@Slf4j
@Service("gameEveItemService")
public class GameEveItemService implements ItemService {

    private final GameEveAsyncTask asyncTask;

    private final GameEveItemRepository itemRepository;

    public GameEveItemService(
            @Qualifier("gameEveItemLoadTask") GameEveAsyncTask asyncTask,
            GameEveItemRepository itemRepository) {
        this.asyncTask = asyncTask;
        this.itemRepository = itemRepository;
    }

    /**
     * 加载游戏EVE物品/商品数据文件
     * <p>Load the game EVE item data file.
     *
     * @param file 文件路径 (File path)
     */
    @Override
    public final <T> void load(T file) {

        log.debug("Thread: " + Thread.currentThread().getName() + " load executing");

        var scanner = FileLoader.getScanner(String.valueOf(file), GameEvePattern.getFile());

        if (scanner == null) {
            log.error("File loaded failed!");
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

                asyncTask.run(scannerMaps.get(index));
                scannerMaps.add(new ArrayList<>());
                index++;
            }
        }
        asyncTask.run(scannerMaps.get(index));

        log.info("Thread: " + Thread.currentThread().getName() + " load executed completely!");
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

    // public BigDecimal getCurPrice(String itemId) {
    //     var item = gameEveItemJpaRepository.findById(itemId).orElse(null);
    //     if (item == null) {
    //         return null;
    //     }
    //     return item.getPrice().getCurPrice();
    // }

    // public BigDecimal getCurPrice(List<GameEveItem> gameEveItems, String itemId) {
    //     return gameEveItems.stream().filter(item -> item.getItemId().equals(itemId)).map(GameEveItem::getPrice).map(Price::getCurPrice).filter(Objects::nonNull).findFirst().orElse(null);
    // }

    /**
     * 分页查询并生成已发布的物品/商品编号数据流
     * <p>Query and generate a published item id data stream pageable.
     *
     * @param pageable 分页
     * @return {@link Stream}<{@link GameEveItem}>
     */
    public Slice<GameEveItem> findItemIdSliceByPublished(Pageable pageable) {
        return itemRepository.findItemIdSliceByPublished(pageable);
    }


    /**
     * 查询并计算已发布的物品/商品数量
     * <p>Query and calculate the number of items that have been released.
     *
     * @return long
     */
    public long countByPublished() {
        return itemRepository.countByPublished();
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
