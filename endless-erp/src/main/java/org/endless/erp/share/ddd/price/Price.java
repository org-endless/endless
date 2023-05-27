package org.endless.erp.share.ddd.price;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * Price
 * <p>价格模板类
 * <p>The price model.
 *
 * <p>create 2023/05/26 15:40
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Price {

    /**
     * 现价
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal currentPrice;

    /**
     * 预估价格
     * <p> 根据每日销量估算出可以达到10%利润的价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal estimatedPrice;

    /**
     * 出售价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal sellPrice;

    /**
     * 采购价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal purchasePrice;

    /**
     * 上日价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal previousDayPrice;

    /**
     * 上次价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal lastPrice;

    /**
     * 最低价
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal minPrice;

    /**
     * 最高价
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal maxPrice;

    /**
     * 基础价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal basePrice;

    /**
     * 平均价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averagePrice;

    /**
     * 价格中位数
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianPrice;

    /**
     * 过去一周平均价格，从当日开始计算一周
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averagePriceLastWeek;

    /**
     * 过去一周价格中位数，从当日开始计算一周
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianPriceLastWeek;

    /**
     * 过去一月平均价格，从当日开始计算一月
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averagePriceLastMonth;

    /**
     * 过去一月价格中位数，从当日开始计算一月
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianPriceLastMonth;

    /**
     * 过去一个季度平均价格，从当日开始计算一个季度
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averagePriceLastQuarter;

    /**
     * 过去一个季度价格中位数，从当日开始计算一个季度
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianPriceLastQuarter;

    /**
     * 过去半年平均价格，从当日开始计算半年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averagePriceLastHalfYear;

    /**
     * 过去半年价格中位数，从当日开始计算半年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianPriceLastHalfYear;

    /**
     * 过去一年平均价格，从当日开始计算一年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averagePriceLastYear;

    /**
     * 过去一年价格中位数，从当日开始计算一年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianPriceLastYear;

    /**
     * 过去三年平均价格，从当日开始计算三年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal averagePriceLastThreeYear;

    /**
     * 过去三年价格中位数，从当日开始计算三年
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal medianPriceLastThreeYear;

}