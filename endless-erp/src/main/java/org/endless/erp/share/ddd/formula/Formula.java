package org.endless.erp.share.ddd.formula;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.ddd.name.Name;
import org.endless.erp.share.ddd.price.Price;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

/**
 * Formula
 * <p>配方模板类
 * <p>Formula model for erp
 *
 * @author Deng Haozhi
 * @date 2022/11/18 13:01
 * @since 0.0.1
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
    private BigDecimal productionPerCycle;

    /**
     * 成功率/良品率
     */
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
     * 更新时间
     */
    @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
    private String updateDateTime;

    /**
     * 更新时间戳
     */
    @Pattern(regexp = Regular.TIME, message = Regular.TIME_MESSAGE)
    private long updateTimeStamp;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Material {

        @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
        private String itemId;

        private BigDecimal quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Skill {

        @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
        private String itemId;

        private String level;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimePerCycle {

        /**
         * 每流程/周期耗时，单位秒
         */
        private BigDecimal secondsPerCycle;

        /**
         * 每流程/周期耗时，单位分钟
         */
        private BigDecimal minutesPerCycle;

        /**
         * 每流程/周期耗时，单位小时
         */
        private BigDecimal hoursPerCycle;

        /**
         * 每流程/周期耗时，单位天
         */
        private BigDecimal daysPerCycle;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MaxProductionLimit {

        /**
         * 每产线产量上限
         */
        private BigDecimal maxProductionLimitPerLine;

    }
}