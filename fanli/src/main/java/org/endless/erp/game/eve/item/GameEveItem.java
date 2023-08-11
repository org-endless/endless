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
 * GameEveItem
 * <p>游戏EVE物品/商品类，映射数据文档game.eve.item
 * <p>Game EVE item class mapping the data document named game.eve.item.
 * <p>
 * <p>create 2023/03/07 1:28
 * <p>update 2023/05/28 00:35
 *
 * @author Deng Haozhi
 * @see Item
 * @since 0.0.3
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
