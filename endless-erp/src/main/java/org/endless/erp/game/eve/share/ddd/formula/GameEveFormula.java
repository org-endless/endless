package org.endless.erp.game.eve.share.ddd.formula;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.endless.erp.share.ddd.formula.Formula;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

/**
 * GameEveFormula for EVE
 *
 * @author Deng Haozhi
 * @date 2023/4/11 16:51
 * @since 0.0.2
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Document("game.eve.formula")
public class GameEveFormula extends Formula {

    /**
     * 配方类别
     */
    private Categories categories;

    public enum Categories {

        manufacturing("0", "manufacturing", "制造"),
        invention("1", "invention", "发明"),
        copying("2", "copying", "拷贝"),
        researchMaterial("3", "researchMaterial", "材料成本研究"),
        researchTime("4", "researchTime", "时间成本研究"),
        planet("5", "planet", "行星工业");

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