package org.endless.erp.share.ddd.saleHistory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.ddd.price.Price;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SaleHistory
 * <p>销售历史模板
 * <p>The model of sale saleHistory.
 *
 * <p>create 2023/5/26 15:43
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class SaleHistory {

    /**
     * 复合编号：行业编号+"_"+日期+"_"+物品/商品编号
     */
    @Id
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String id;

    /**
     * 物品/商品编号
     */
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String itemId;

    /**
     * 行业编号
     */
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String industryId;

    /**
     * 日期
     */
    @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
    private Date date;

    /**
     * 价格
     */
    private Price price;

    /**
     * 总数量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal totalQuantity;

    /**
     * 订单数量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal orderQuantity;

    /**
     * 版本，乐观锁
     */
    @Version
    private Long version;

    /**
     * 更新时间
     */
    private String updateDateTime;

    /**
     * 更新时间戳
     */
    private long updateTimeStamp;
}