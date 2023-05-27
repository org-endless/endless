package org.endless.erp.share.ddd.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.ddd.cast.Cost;
import org.endless.erp.share.ddd.name.Name;
import org.endless.erp.share.ddd.price.Price;
import org.endless.erp.share.ddd.profit.Profit;
import org.endless.erp.share.ddd.sales.Sales;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.validation.annotation.Validated;

/**
 * Item
 * <p>物品/商品模板
 * <p>The item model.
 *
 * <p>create 2023/05/26 16:28
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Item {

    /**
     * 复合编号：行业编号+"_"+物品/商品编号
     */
    @Id
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String id;

    /**
     * 物品/商品编号
     */
    @Indexed
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String itemId;

    /**
     * 行业编号
     */
    @Indexed
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String industryId;

    /**
     * 名称
     */
    private Name name;

    /**
     * 价格
     */
    private Price price;

    /**
     * 市场价格
     */
    private Price marketPrice;

    /**
     * 成本
     */
    private Cost cost;

    /**
     * 利润
     */
    private Profit profit;

    /**
     * 销量
     */
    private Sales sales;

    /**
     * 质量/重量
     */
    private String mass;

    /**
     * 体积
     */
    private String volume;

    /**
     * 是否发布
     */
    @Indexed
    private boolean published;

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