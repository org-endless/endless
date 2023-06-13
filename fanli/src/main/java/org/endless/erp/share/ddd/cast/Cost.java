package org.endless.erp.share.ddd.cast;

import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.validation.annotation.Validated;

/**
 * @author Deng Haozhi
 * @date 2023/4/28 15:25
 * @since 0.0.2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Cost {

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal manufacturingCost;

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal manufacturingProcurementCost;

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal tradingCost;

  private Boolean isManufacturing;
}
