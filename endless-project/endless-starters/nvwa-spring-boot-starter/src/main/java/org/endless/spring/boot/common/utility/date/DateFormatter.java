package org.endless.spring.boot.common.utility.date;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * DateFormatter
 *
 * @author Deng Haozhi
 * @date 2023/3/16 7:32
 * @since 0.0.2
 */
public class DateFormatter {
    public static String today() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return ZonedDateTime.now().format(dateTimeFormatter);
    }

    public static String now() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
        return ZonedDateTime.now().format(dateTimeFormatter);
    }

    public static String nowIso() {

        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.UK);
        return ZonedDateTime.now().format(dateTimeFormatter);
    }

    public static String nowStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String isoToDate(String iso) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return ZonedDateTime.parse(iso).format(dateTimeFormatter);
    }

    public static String isoToTime(String iso) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
        return ZonedDateTime.parse(iso).format(dateTimeFormatter);
    }
}
