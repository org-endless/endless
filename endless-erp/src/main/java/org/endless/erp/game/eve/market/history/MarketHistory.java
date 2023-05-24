package org.endless.erp.game.eve.market.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.ddd.price.Price;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * MarketHistory for EVE
 *
 * @author Deng Haozhi
 * @date 2023/4/23 16:25
 * @since 0.0.2
 */
@Document("eve.market.history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class MarketHistory {

    private String date;

    private Price price;

    private String orderCount;

    private String volume;

}