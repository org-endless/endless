package org.endless.erp.share.ddd.name;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.validation.annotation.Validated;

/**
 * Name
 *
 * @author Deng Haozhi
 * @date 2022/11/17 22:48
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
@Validated
public class Name {

    private NameDetail enName;

    private NameDetail zhName;

    public Name() {
        this.enName = new NameDetail();
        this.zhName = new NameDetail();
    }

    /**
     * NameDetail
     *
     * @author Deng Haozhi
     * @date 2023/3/12 18:24
     * @since 0.0.2
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NameDetail {
        // 全称
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String fullName;
        // 名
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String firstName;
        // 姓
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String lastName;
        // 别名
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String alias;
        // 简称
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String abbreviation;
    }
}