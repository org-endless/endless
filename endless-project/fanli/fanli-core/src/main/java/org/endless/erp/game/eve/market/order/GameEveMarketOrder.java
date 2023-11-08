package org.endless.erp.game.eve.market.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.endless.erp.share.ddd.order.Order;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * GameEveMarketOrder for EVE
 *
 * @author Deng Haozhi
 * @date 2023/4/8 22:28
 * @since 0.0.2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@Document("game.eve.market.order")
public class GameEveMarketOrder extends Order {
}
