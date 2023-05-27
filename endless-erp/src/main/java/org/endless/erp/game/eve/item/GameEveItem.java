package org.endless.erp.game.eve.item;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.endless.erp.share.ddd.item.Item;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * GameEveItem for EVE
 *
 * @author Deng Haozhi
 * @date 2023/3/7 1:28
 * @since 0.0.2
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Document("game.eve.item")
public class GameEveItem extends Item {

    /**
     * EVE分组编号
     */
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String eveGroupId;

    /**
     * EVE市场分组编号
     */
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String eveMarketGroupId;

    /**
     * EVE类型分组编号
     */
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String eveMetaGroupId;
}