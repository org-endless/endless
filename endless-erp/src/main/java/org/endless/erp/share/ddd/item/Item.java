package org.endless.erp.share.ddd.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.endless.erp.share.ddd.cast.Cost;
import org.endless.erp.share.ddd.price.Price;
import org.endless.erp.share.ddd.profit.Profit;
import org.endless.erp.share.ddd.sales.Sales;
import org.endless.erp.share.ddd.name.Name;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * Item
 * <p>物品/商品模板类
 * <p>Item model for ERP.
 *
 * @author Deng Haozhi
 * @date 2022/11/17 21:12
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
@Validated
public class Item {

    /**
     * 复合编号：行业编号+"_"+物品/商品编号
     */
    @Id
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
     * 名称
     */
    private Name name;

    /**
     * 价格
     */
    private Price price;

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
    private boolean published;

    /**
     * 更新时间
     */
    @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
    private String updateDateTime;

    /**
     * 更新时间戳
     */
    @Pattern(regexp = Regular.TIME, message = Regular.TIME_MESSAGE)
    private long updateTimeStamp;

    public Item() {
        this.name = new Name();
        this.price = new Price();
        this.cost = new Cost();
        this.profit = new Profit();
        this.sales = new Sales();
    }
}