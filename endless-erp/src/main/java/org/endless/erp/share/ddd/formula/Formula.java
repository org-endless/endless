package org.endless.erp.share.ddd.formula;

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
import java.util.List;

/**
 * Formula
 * <p>配方模板
 * <p>The formula model.
 *
 * <p>create 2023/05/26 16:19
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Formula {

    /**
     * 复合编号：行业编号+"_"+配方类别+"_"+物品/商品编号
     * 配方类别根据场景在子类中定义
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
     * 配方编号
     */
    @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
    private String formulaItemId;

    /**
     * 每流程/周期产量
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal productionPerCycle;

    /**
     * 成功率/良品率
     */
    @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
    private BigDecimal probability;

    /**
     * 每流程/周期耗时
     */
    private TimePerCycle timePerCycle;

    /**
     * 产量上限
     */
    private MaxProductionLimit maxProductionLimit;

    /**
     * 原料
     */
    private List<Material> materials;

    /**
     * 技能/资质
     */
    private List<Skill> skills;

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


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Material {

        /**
         * 物品/商品编号
         */
        @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
        private String itemId;

        /**
         * 数量
         */
        @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
        private BigDecimal quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Skill {

        /**
         * 物品/商品编号
         */
        @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
        private String itemId;

        /**
         * 级别
         */
        private String level;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimePerCycle {

        /**
         * 每流程/周期耗时，单位秒
         */
        @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
        private BigDecimal secondsPerCycle;

        /**
         * 每流程/周期耗时，单位分钟
         */
        @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
        private BigDecimal minutesPerCycle;

        /**
         * 每流程/周期耗时，单位小时
         */
        @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
        private BigDecimal hoursPerCycle;

        /**
         * 每流程/周期耗时，单位天
         */
        @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
        private BigDecimal daysPerCycle;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MaxProductionLimit {

        /**
         * 每产线产量上限
         */
        @Pattern(regexp = Regular.DECIMAL, message = Regular.DECIMAL_MESSAGE)
        private BigDecimal maxProductionLimitPerLine;

    }
}