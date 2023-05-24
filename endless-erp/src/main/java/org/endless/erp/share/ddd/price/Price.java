package org.endless.erp.share.ddd.price;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * 价格类
 * Price for Erp.
 *
 * @author Deng Haozhi
 * @date 2022/11/17 21:12
 * @since 0.0.1
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
    private BigDecimal curPrice;

    /**
     * 上日价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal prePrice;

    /**
     * 上次价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal lastPrice;
    // 平均价格
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal avgPrice;
    // 最低价
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal minPrice;
    // 最高价
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal maxPrice;
    // 市场价格
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal marketPrice;
    // 基础价格
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal basePrice;
    // 采购价格
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal buyPrice;

    // 预估价格
//     利润 10% 然后 销量满足每日销量
}