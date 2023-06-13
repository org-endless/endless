package org.endless.erp.share.pattern;

import java.util.regex.Pattern;

/**
 * Regular
 *
 * @author Deng Haozhi
 * @date 2022/11/18 16:50
 * @since 0.0.1
 */
public class Regular {
  // 正则表达式
  // 编号 Char(200)
  public static final String ID = "^{1,50}$";
  public static final String ID_MESSAGE = "Must be less than 50 characters!";
  // 姓名 Char(200)
  public static final String NAME = "^{1,200}$";
  public static final String NAME_MESSAGE = "Must be less than 200 characters!";
  // 价格 Decimal(22,2)
  public static final String DECIMAL = "^[0-9]{1,20}+(.[0-9]{2})?$";
  public static final String DECIMAL_MESSAGE =
      "Must be the right DECIMAL, less than 22 numbers, accurate to 0.01!";
  // 日期 yyyyMMdd
  public static final String DATE =
      "^((\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2}|[1-9]\\d{3})-(((0[13578]|1[02])-(0[1-9]|[12]\\d|3[01]))|((0[469]|11)-(0[1-9]|[12]\\d|30))|(02-(0[1-9]|[1]\\d|2[0-8]))))|(((\\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
  public static final String DATE_MESSAGE = "Must be the right DATE, yyyy-MM-dd!";
  // 时间 HHmmssSSS
  public static final String TIME =
      "^((\\d{3}[1-9]|\\d{2}[1-9]\\d|\\d[1-9]\\d{2}|[1-9]\\d{3})(((0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|((0[469]|11)(0[1-9]|[12]\\d|30))|(02(0[1-9]|[1]\\d|2[0-8]))))|(((\\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)$";
  public static final String TIME_MESSAGE = "Must be the right TIME, HHmmssSSS!";

  public static Pattern get(String regular) {
    return Pattern.compile(regular);
  }

  public static Pattern get(String regular, int flags) {
    return Pattern.compile(regular, flags);
  }
}
