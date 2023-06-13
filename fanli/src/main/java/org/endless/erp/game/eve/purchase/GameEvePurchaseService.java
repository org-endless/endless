package org.endless.erp.game.eve.purchase;

import org.endless.erp.game.eve.formula.GameEveFormulaService;
import org.endless.erp.game.eve.item.GameEveItemService;
import org.springframework.stereotype.Service;

/**
 * GameEvePurchaseService
 *
 * @author Deng Haozhi
 * @date 2023/5/6 20:05
 * @since 0.0.2
 */
@Service
public class GameEvePurchaseService {

    private final GameEveItemService gameEveItemService;

    private final GameEveFormulaService gameEveFormulaService;

    public GameEvePurchaseService(GameEveItemService gameEveItemService, GameEveFormulaService gameEveFormulaService) {
        this.gameEveItemService = gameEveItemService;
        this.gameEveFormulaService = gameEveFormulaService;
    }

    // public List<Map<String, String>> getManufacturingProcurementList(List<String> itemIds) {
    //
    //     var purchaseQuantity = getManufacturingPurchaseQuantity(itemIds);
    //     var items = gameEveItemService.getItemsByItemIds(purchaseQuantity.keySet().stream().toList());
    //     List<Map<String, String>> purchaseList = new ArrayList<>();
    //     items.forEach(item -> {
    //         Map<String, String> purchase = new HashMap<>();
    //         purchase.put("itemId", item.getItemId());
    //         purchase.put("name", item.getName().getZhName().getFullName());
    //         purchase.put("quantity", String.valueOf(purchaseQuantity.get(item.getItemId())));
    //         purchase.put("isManufacturing", String.valueOf(item.getCost().getIsManufacturing()));
    //         purchase.put("procurementDifference", String.valueOf(Decimal.format(Decimal.multiply(purchaseQuantity.get(item.getItemId()), Decimal.subtract(item.getPrice().getCurPrice(), item.getCost().getManufacturingCost())))));
    //         purchaseList.add(purchase);
    //     });
    //     return purchaseList;
    // }

    // public Map<String, BigDecimal> getManufacturingPurchaseQuantity(List<String> itemIds) {
    //
    //     var blueprints = gameEveFormulaService.getAll();
    //     return itemIds.stream().map(itemId -> gameEveFormulaService.getManufacturingBlueprint(blueprints, itemId)).flatMap(blueprint -> blueprint.getManufacturing().getMaterials().stream()).collect(Collectors.toMap(Material::getItemId, Material::getQuantity, Decimal::add)).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, procurement -> Decimal.format(procurement.getValue()), (a, b) -> b));
    // }
}