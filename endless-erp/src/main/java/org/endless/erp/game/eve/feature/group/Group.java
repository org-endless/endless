package org.endless.erp.game.eve.feature.group;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.endless.erp.share.ddd.name.Name;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;


/**
 * Group for EVE
 *
 * @author Deng Haozhi
 * @date 2023/3/7 1:28
 * @since 0.0.2
 */
@Document("eve.group")
@Data
@AllArgsConstructor
@Validated
public class Group {
    @Id
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String groupId;

    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String categoryId;

    private Name name;

    private boolean published;

    @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
    private String updateDateTime;

    @Pattern(regexp = Regular.TIME, message = Regular.TIME_MESSAGE)
    private long updateTimeStamp;

    public Group() {
        this.name = new Name();
    }
}