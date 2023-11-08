package org.endless.erp.share.ddd.sales;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * Sales
 *
 * <p>销量模板类
 *
 * <p>The sales model.
 *
 * <p>create 2023/05/26 15:39
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Sales {

    /**
     * 上一日销量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesPreviousDay;

    /**
     * 上一周销量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesPreviousWeek;

    /**
     * 上一月销量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesPreviousMonth;

    /**
     * 上一季度销量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesPreviousQuarter;

    /**
     * 上一半年销量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesPreviousHalfYear;

    /**
     * 上一年销量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesPreviousYear;

    /**
     * 过去一周销量，从当日开始计算一周
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesLastWeek;

    /**
     * 过去一周日平均销量，从当日开始计算一周
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averageDailySalesLastWeek;

    /**
     * 过去一周日销量中位数，从当日开始计算一周
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianDailySalesLastWeek;

    /**
     * 过去一月销量，从当日开始计算一月
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesLastMonth;

    /**
     * 过去一月日平均销量，从当日开始计算一月
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averageDailySalesLastMonth;

    /**
     * 过去一月日销量中位数，从当日开始计算一月
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianDailySalesLastMonth;

    /**
     * 过去一个季度销量，从当日开始计算一个季度
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesLastQuarter;

    /**
     * 过去一个季度日平均销量，从当日开始计算一个季度
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averageDailySalesLastQuarter;

    /**
     * 过去一个季度日销量中位数，从当日开始计算一个季度
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianDailySalesLastQuarter;

    /**
     * 过去半年销量，从当日开始计算半年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesLastHalfYear;

    /**
     * 过去半年日平均销量，从当日开始计算半年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averageDailySalesLastHalfYear;

    /**
     * 过去半年日销量中位数，从当日开始计算半年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianDailySalesLastHalfYear;

    /**
     * 过去一年销量，从当日开始计算一年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesLastYear;

    /**
     * 过去一年日平均销量，从当日开始计算一年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averageDailySalesLastYear;

    /**
     * 过去一年日销量中位数，从当日开始计算一年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianDailySalesLastYear;

    /**
     * 过去三年销量，从当日开始计算三年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal salesLastThreeYear;

    /**
     * 过去三年日平均销量，从当日开始计算三年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averageDailySalesLastThreeYear;

    /**
     * 过去三年日销量中位数，从当日开始计算三年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianDailySalesLastThreeYear;
}
