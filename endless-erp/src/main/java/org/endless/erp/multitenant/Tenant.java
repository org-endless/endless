package org.endless.erp.multitenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Deng Haozhi
 * @date 2023/5/16 10:17
 * @since 0.0.2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("tenant")
public class Tenant {

    private String tenantId;

    private String industryId;
}