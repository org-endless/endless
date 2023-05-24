package org.endless.erp.share.ddd.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * @author Deng Haozhi
 * @date 2023/4/30 1:45
 * @since 0.0.2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Sales {

    private BigDecimal salesByLastDay;
    private BigDecimal salesByLastWeek;
    private BigDecimal salesPerDayByWeek;
    private BigDecimal salesByLastHalfMonth;
    private BigDecimal salesPerDayByHalfMonth;
    private BigDecimal salesByLastMonth;
    private BigDecimal salesPerDayByMonth;
    private BigDecimal salesByLastQuarter;
    private BigDecimal salesPerDayByQuarter;
    private BigDecimal salesByLastHalfYear;
    private BigDecimal salesPerDayByHalfYear;
    private BigDecimal salesByLastYear;
    private BigDecimal dailyMarketSalesByYear;

}