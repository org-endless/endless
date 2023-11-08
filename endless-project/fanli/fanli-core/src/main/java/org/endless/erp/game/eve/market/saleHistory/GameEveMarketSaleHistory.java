package org.endless.erp.game.eve.market.saleHistory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.endless.erp.share.ddd.saleHistory.SaleHistory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * GameEveMarketSaleHistory
 * <p>游戏EVE中的市场销售历史
 * <p>The market sale history for game EVE.
 *
 * <p>create 2023/05/26 16:15
 *
 * @author Deng Haozhi
 * @see SaleHistory
 * @since 0.0.3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@Document("game.eve.market.sale.history")
public class GameEveMarketSaleHistory extends SaleHistory {
}
