package org.endless.erp.share.ddd.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * Order
 * <p>订单模板类
 * <p>The order model.
 *
 * <p>create 2023/05/26 15:41
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Order {

    /**
     * 复合编号：行业编号+"_"+订单编号
     */
    @Id
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String id;

    /**
     * 订单编号
     */
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String orderId;

    /**
     * 行业编号
     */
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String industryId;

    /**
     * 物品/商品编号
     */
    @NotEmpty
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String itemId;

    /**
     * 订单类型
     */
    private Categories categories;

    /**
     * 最小购买数量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal minPurchaseQuantity;

    /**
     * 剩余数量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal remainQuantity;

    /**
     * 总数量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal totalQuantity;

    /**
     * 订单价格
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal orderPrice;

    /**
     * 是否存在
     */
    private Boolean existed;

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

    /**
     * Categories
     * <p>订单类型枚举
     *
     * <p>create 2023/05/26 16:42
     *
     * @author Deng Haozhi
     * @see Enum
     * @since 0.0.3
     */
    public enum Categories {

        sale("0", "sale", "出售"),
        purchase("1", "purchase", "采购");

        /**
         * 类型编号
         */
        private final String categoriesId;

        /**
         * 英文说明
         */
        private final String enInstruction;

        /**
         * 中文说明
         */
        private final String zhInstruction;

        Categories(String categoriesId, String enInstruction, String zhInstruction) {
            this.categoriesId = categoriesId;
            this.enInstruction = enInstruction;
            this.zhInstruction = zhInstruction;
        }

        public String getCategoriesId() {
            return categoriesId;
        }

        public String getEnInstruction() {
            return enInstruction;
        }

        public String getZhInstruction() {
            return zhInstruction;
        }
    }
}