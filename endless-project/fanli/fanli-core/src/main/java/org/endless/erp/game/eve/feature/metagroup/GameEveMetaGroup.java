package org.endless.erp.game.eve.feature.metagroup;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.ddd.name.Name;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * GameEveMetaGroup for EVE
 *
 * <p>update 2023/4/20 18:25
 * <p>update 2023/8/15 21:04
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */

@Document("game.eve.meta.group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class GameEveMetaGroup {

    @Id
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String metaGroupId;

    private Name name;

}
