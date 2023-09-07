package org.endless.erp.game.eve.feature.marketgroup;

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
 * GameEveMarketGroup
 *
 * <p>create 2023/4/20 17:53
 * <p>update 2023/8/15 21:02
 *
 * @author Deng Haozhi
 * @since 0.0.2
 */

@Document("game.eve.market.group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class GameEveMarketGroup {

    @Id
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String marketGroupId;

    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String parentGroupId;

    private Name name;

    private boolean hasTypes;

    @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
    private String updateDateTime;

    @Pattern(regexp = Regular.TIME, message = Regular.TIME_MESSAGE)
    private long updateTimeStamp;
}
