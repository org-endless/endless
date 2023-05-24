package org.endless.erp.share.ddd.industry;

import jakarta.validation.constraints.NotEmpty;
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
 * Industry
 *
 * @author Deng Haozhi
 * @date 2023/5/16 10:23
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Document("industry")
public class Industry {

    @Id
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String industryId;

    private Name industryName;
}