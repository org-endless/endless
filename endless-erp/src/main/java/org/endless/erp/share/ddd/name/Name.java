package org.endless.erp.share.ddd.name;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.validation.annotation.Validated;

/**
 * Name
 * <p>姓名模板类
 * <p>The name model.
 *
 * <p>create 2023/05/26 16:36
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Name {

    /**
     * 英文名
     */
    private NameDetail enName;

    /**
     * 中文名
     */
    private NameDetail zhName;

    /**
     * NameDetail
     * <p>姓名详情
     * <p>The name detail.
     *
     * <p>create 2023/05/26 16:38
     *
     * @author Deng Haozhi
     * @since 0.0.3
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NameDetail {

        /**
         * 全名
         */
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String fullName;

        /**
         * 名
         */
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String firstName;

        /**
         * 姓
         */
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String lastName;

        /**
         * 别名
         */
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String alias;

        /**
         * 简称
         */
        @Pattern(regexp = Regular.NAME, message = Regular.NAME_MESSAGE)
        private String abbreviation;
    }
}