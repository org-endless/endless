package org.endless.erp.share.ddd.profit;

import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.validation.annotation.Validated;

/**
 * @author Deng Haozhi
 * @date 2023/4/28 15:33
 * @since 0.0.2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Profit {

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal manufacturingProfit;

  private BigDecimal manufacturingProfitMargin;

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal manufacturingProfitPerDay;

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal manufacturingProcurementProfit;

  private BigDecimal manufacturingProcurementProfitMargin;

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal manufacturingProcurementProfitPerDay;

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal tradingProfit;

  private BigDecimal tradingProfitMargin;

  @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
  private BigDecimal tradingProfitPerDay;
}
