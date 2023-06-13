package org.endless.erp.game.eve.feature.category;

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
 * Category for EVE
 *
 * @author Deng Haozhi
 * @date 2023/4/20 17:34
 * @since 0.0.2
 */
@Document("eve.category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Category {

  @Id
  @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
  private String categoryId;

  private Name name;

  private boolean published;

  @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
  private String updateDateTime;

  @Pattern(regexp = Regular.TIME, message = Regular.TIME_MESSAGE)
  private long updateTimeStamp;
}
