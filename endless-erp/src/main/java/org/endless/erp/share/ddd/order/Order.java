package org.endless.erp.share.ddd.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.game.eve.share.ddd.formula.GameEveFormula;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * Order
 *
 * @author Deng Haozhi
 * @date 2023/5/24 9:49
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
     * 是否存在
     */
    private Boolean existed;

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

    public enum Categories {

        sale("0", "sale", "出售"),
        purchase("1", "purchase", "采购");

        private final String categoriesId;
        private final String enInstruction;
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