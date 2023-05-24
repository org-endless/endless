package org.endless.demo;

/**
 * Name
 *
 * @author Deng Haozhi
 * @date 2022/11/17 22:48
 * @since 0.0.1
 */
public class Name {

    private NameDetail enName;

    private NameDetail zhName;

    public Name() {
        this.enName = new Name.NameDetail();
        this.zhName = new Name.NameDetail();
    }

    public NameDetail getEnName() {
        return enName;
    }

    public void setEnName(NameDetail enName) {
        this.enName = enName;
    }

    public NameDetail getZhName() {
        return zhName;
    }

    public void setZhName(NameDetail zhName) {
        this.zhName = zhName;
    }

    public Name(NameDetail enName, NameDetail zhName) {
        this.enName = enName;
        this.zhName = zhName;
    }

    /**
     * NameDetail
     *
     * @author Deng Haozhi
     * @date 2023/3/12 18:24
     * @since 0.0.2
     */

    public static class NameDetail {
        // 全称
        private String fullName;
        // 名
        private String firstName;
        // 姓
        private String lastName;
        // 别名
        private String alias;
        // 简称
        private String abbreviation;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public NameDetail() {
        }

        public NameDetail(String fullName, String firstName, String lastName, String alias, String abbreviation) {
            this.fullName = fullName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.alias = alias;
            this.abbreviation = abbreviation;
        }
    }
}