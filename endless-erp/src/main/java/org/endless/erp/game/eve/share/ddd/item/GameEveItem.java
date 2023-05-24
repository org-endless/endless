package org.endless.erp.game.eve.share.ddd.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.endless.erp.share.ddd.item.Item;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
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
    private String groupId;

    /**
     * EVE市场分组编号
     */
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String marketGroupId;

    /**
     * EVE类型分组编号
     */
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String metaGroupId;
}