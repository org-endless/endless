package org.endless.erp.game.eve.profit;

import org.endless.erp.game.eve.share.ddd.formula.GameEveFormula;
import org.endless.erp.game.eve.share.ddd.formula.GameEveFormulaService;
import org.endless.erp.game.eve.share.ddd.item.GameEveItem;
import org.endless.erp.game.eve.share.ddd.item.GameEveItemService;
import org.endless.erp.share.mongo.bulk.BulkMongoRepository;
import org.endless.erp.share.util.decimal.Decimal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.endless.erp.share.constant.ConstantResource.PAGE_SIZE;

/**
 * @author Deng Haozhi
 * @date 2023/4/28 22:48
 * @since 0.0.2
 */
@Service
public class GameEveProfitService {

    // private final BulkMongoRepository bulkMongoRepository;
    //
    // private final GameEveItemService gameEveItemService;
    //
    // private final GameEveFormulaService gameEveFormulaService;
    //
    // public GameEveProfitService(BulkMongoRepository bulkMongoRepository, GameEveItemService gameEveItemService, GameEveFormulaService gameEveFormulaService) {
    //     this.bulkMongoRepository = bulkMongoRepository;
    //     this.gameEveItemService = gameEveItemService;
    //     this.gameEveFormulaService = gameEveFormulaService;
    // }

    public void update() {
        //
        // Pageable pageable = PageRequest.of(0, PAGE_SIZE);
        // List<Pair<Query, Update>> upsertList = new ArrayList<>();
        //
        // var blueprints = gameEveFormulaService.getAll();
        //
        // while (true) {
        //     var slice = gameEveItemService.getSliceByPublished(true, pageable);
        //
        //     for (GameEveItem gameEveItem : slice.getContent()) {
        //         var blueprint = gameEveFormulaService.getManufacturingBlueprint(blueprints, gameEveItem.getItemId());
        //         if (blueprint == null) continue;
        //         var query = Query.query(Criteria.where("itemId").is(gameEveItem.getItemId()));
        //         var update = Update.update("profit.manufacturingProfit", Decimal.format128(getManufacturingProfit(gameEveItem)))
        //                 .set("profit.manufacturingProfitMargin", Decimal.format128(getManufacturingProfitMargin(gameEveItem)))
        //                 .set("profit.manufacturingProfitPerDay", Decimal.format128(getManufacturingProfitPerDay(gameEveItem, blueprint)))
        //                 .set("profit.manufacturingProcurementProfit", Decimal.format128(getManufacturingProcurementProfit(gameEveItem)))
        //                 .set("profit.manufacturingProcurementProfitMargin", Decimal.format128(getManufacturingProcurementProfitMargin(gameEveItem)))
        //                 .set("profit.manufacturingProcurementProfitPerDay", Decimal.format128(getManufacturingProcurementProfitPerDay(gameEveItem, blueprint)))
        //                 .set("profit.tradingProfit", Decimal.format128(getTradingProfit(gameEveItem)))
        //                 .set("profit.tradingProfitMargin", Decimal.format128(getTradingProfitMargin(gameEveItem)))
        //                 .set("profit.tradingProfitPerDay", Decimal.format128(getTradingProfitPerDay(gameEveItem)));
        //         upsertList.add(Pair.of(query, update));
        //     }
        //     if (!slice.hasNext()) break;
        //     pageable = slice.nextPageable();
        // }
        // bulkMongoRepository.upsert(upsertList, 1000, GameEveItem.class);
        // System.out.println("GameEveItemService updateProfit executed!");
    }

    protected BigDecimal getManufacturingProcurementProfit(GameEveItem gameEveItem) {
        return Decimal.subtract(getIncome(gameEveItem), getProcurementCost(gameEveItem));
    }

    protected BigDecimal getManufacturingProcurementProfitMargin(GameEveItem gameEveItem) {
        return Decimal.multiply(new BigDecimal("100"), Decimal.divide(getManufacturingProcurementProfit(gameEveItem), gameEveItem.getCost().getManufacturingProcurementCost()));
    }

    protected BigDecimal getManufacturingProcurementProfitPerDay(GameEveItem gameEveItem, GameEveFormula gameEveFormula) {
        return Decimal.multiply(getDailySales(gameEveItem, gameEveFormula), getManufacturingProcurementProfit(gameEveItem));
    }

    protected BigDecimal getManufacturingProfit(GameEveItem gameEveItem) {
        return Decimal.subtract(getIncome(gameEveItem), getCost(gameEveItem));
    }

    protected BigDecimal getManufacturingProfitMargin(GameEveItem gameEveItem) {
        return Decimal.multiply(new BigDecimal("100"), Decimal.divide(getManufacturingProfit(gameEveItem), gameEveItem.getCost().getManufacturingCost()));
    }

    protected BigDecimal getManufacturingProfitPerDay(GameEveItem gameEveItem, GameEveFormula gameEveFormula) {
        return Decimal.multiply(getDailySales(gameEveItem, gameEveFormula), getManufacturingProfit(gameEveItem));
    }

    protected BigDecimal getTradingProfit(GameEveItem gameEveItem) {
        return Decimal.subtract(gameEveItem.getPrice().getCurPrice(), gameEveItem.getPrice().getBuyPrice());
    }

    protected BigDecimal getTradingProfitMargin(GameEveItem gameEveItem) {
        return Decimal.multiply(new BigDecimal("100"), Decimal.divide(getTradingProfit(gameEveItem), gameEveItem.getPrice().getBuyPrice()));
    }

    protected BigDecimal getTradingProfitPerDay(GameEveItem gameEveItem) {

        // 日销售系数
        var salesFactor = Decimal.divide(gameEveItem.getSales().getDailyMarketSalesByYear(), new BigDecimal("5"));

        return Decimal.multiply(salesFactor, getTradingProfit(gameEveItem));
    }


    protected BigDecimal getIncome(GameEveItem gameEveItem) {
        // 中介费率
        var intermediaryRate = new BigDecimal("0.03");
        // 销售税率
        var saleTaxRate = new BigDecimal("0.036");
        // 销售费用系数
        var saleExpensesFactor = Decimal.subtract(Decimal.subtract(new BigDecimal("1"), intermediaryRate), saleTaxRate);
        // 收入
        return Decimal.multiply(gameEveItem.getPrice().getCurPrice(), saleExpensesFactor);
    }

    protected BigDecimal getProcurementCost(GameEveItem gameEveItem) {

        // 中介费率
        var intermediaryRate = new BigDecimal("0.00");
        // 星际制造成本率
        var manufacturingCostRate = new BigDecimal("0.04");
        // 制造费用系数
        var manufacturingFactor = Decimal.add(new BigDecimal("1"), Decimal.add(manufacturingCostRate, intermediaryRate));
        // 成本
        return Decimal.multiply(gameEveItem.getCost().getManufacturingProcurementCost(), manufacturingFactor);
    }

    protected BigDecimal getCost(GameEveItem gameEveItem) {

        // 中介费率
        var intermediaryRate = new BigDecimal("0.00");
        // 星际制造成本率
        var manufacturingCostRate = new BigDecimal("0.04");
        // 制造费用系数
        var manufacturingFactor = Decimal.add(new BigDecimal("1"), Decimal.add(manufacturingCostRate, intermediaryRate));
        // 成本
        return Decimal.multiply(gameEveItem.getCost().getManufacturingCost(), manufacturingFactor);
    }

    /**
     * 每日销售量依赖每日产量，产能和销售相关联。
     * <p>Daily sales depends on daily production. Capacity and sales are related
     *
     * @param gameEveItem    EVE物品对象 &nbsp&nbsp The EVE GameEveItem Object.
     * @param gameEveFormula EVE蓝图对象 &nbsp&nbsp The EVE gameEveFormula Object.
     * @return java.math.BigDecimal
     **/

    protected BigDecimal getDailySales(GameEveItem gameEveItem, GameEveFormula gameEveFormula) {

        // var dailyYield = gameEveFormulaService.getManufacturingYieldPerDay(gameEveFormula, gameEveItem.getItemId());

        var dailySales = getDailySales(gameEveItem);

        // if (dailyYield != null && dailySales != null && dailyYield.compareTo(dailySales) < 0) return dailyYield;

        return dailySales;
    }

    /**
     * 每日销售量占比市场每日销售量，根据估算比例获得每日销售量的评估
     * <p>Daily sales as a percentage of daily sales in the market. Get an estimate of daily sales based on the estimated proportion.
     * 每日销量提供查询接口
     * @param gameEveItem EVE物品对象 &nbsp&nbsp The EVE GameEveItem Object.
     * @return java.math.BigDecimal
     **/
    protected BigDecimal getDailySales(GameEveItem gameEveItem) {
        return Decimal.format(Decimal.divide(gameEveItem.getSales().getDailyMarketSalesByYear(), new BigDecimal("1")));
    }

}