package org.endless.erp.share.util.decimal;

import org.bson.types.Decimal128;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

/**
 * Decimal
 *
 * @author Deng Haozhi
 * @date 2023/5/16 22:43
 * @since 0.0.3
 */
public class Decimal {

    public static BigDecimal format(BigDecimal decimal) {

        if (decimal == null) {
            return null;
        }

        return decimal.setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal format(String decimal) {

        if (decimal == null || decimal.isEmpty() || "null".equals(decimal)) {
            return null;
        }

        return new BigDecimal(decimal).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal format(Object decimal) {

        if (decimal == null) {
            return null;
        }

        return new BigDecimal(String.valueOf(decimal)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal format5Bit(BigDecimal decimal) {

        if (decimal == null) {
            return null;
        }

        return decimal.setScale(5, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal format5Bit(String decimal) {

        if (decimal == null || decimal.isEmpty() || "null".equals(decimal)) {
            return null;
        }

        return new BigDecimal(decimal).setScale(5, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal format5Bit(Object decimal) {

        if (decimal == null) {
            return null;
        }

        return new BigDecimal(String.valueOf(decimal)).setScale(5, RoundingMode.HALF_EVEN);
    }

    public static Decimal128 format128(BigDecimal decimal) {

        if (decimal == null) {
            return null;
        }

        return new Decimal128(decimal.setScale(2, RoundingMode.HALF_EVEN));
    }

    public static Decimal128 format128(String decimal) {

        if (decimal == null || decimal.isEmpty() || "null".equals(decimal)) {
            return null;
        }

        return new Decimal128(new BigDecimal(decimal).setScale(2, RoundingMode.HALF_EVEN));
    }

    public static Decimal128 format128(Object decimal) {

        if (decimal == null) {
            return null;
        }

        return new Decimal128(new BigDecimal(String.valueOf(decimal)).setScale(2, RoundingMode.HALF_EVEN));
    }

    public static BigDecimal add(BigDecimal decimal, BigDecimal augend) {

        if (decimal == null || augend == null) {
            return null;
        }

        return format5Bit(decimal.add(augend));
    }

    public static BigDecimal subtract(BigDecimal decimal, BigDecimal subtrahend) {

        if (decimal == null || subtrahend == null) {
            return null;
        }

        return format5Bit(decimal.subtract(subtrahend));
    }

    public static BigDecimal multiply(BigDecimal decimal, BigDecimal multiplicand) {

        if (decimal == null || multiplicand == null) {
            return null;
        }

        return format5Bit(decimal.multiply(multiplicand));
    }

    public static BigDecimal divide(BigDecimal decimal, BigDecimal divisor) {

        if (decimal == null || divisor == null || divisor.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }

        return format5Bit(decimal.divide(divisor, 5, RoundingMode.HALF_EVEN));
    }

    public static BigDecimal max(List<BigDecimal> decimalList) {

        if (decimalList == null || decimalList.isEmpty()) {
            return null;
        }

        BigDecimal max = decimalList.get(0);
        for (var decimal : decimalList) {
            max = max.max(decimal);
        }

        return format(max);
    }

    public static BigDecimal min(List<BigDecimal> decimalList) {

        if (decimalList == null || decimalList.isEmpty()) {
            return null;
        }

        BigDecimal min = decimalList.get(0);
        for (var decimal : decimalList) {
            min = min.min(decimal);
        }

        return format(min);
    }

    public static BigDecimal average(List<BigDecimal> decimalList) {

        if (decimalList == null || decimalList.isEmpty()) {
            return null;
        }

        var sum = BigDecimal.ZERO;
        for (BigDecimal decimal : decimalList) {
            sum = add(sum, decimal);
        }
        return format(divide(sum, format(decimalList.size())));
    }

    public static BigDecimal median(List<BigDecimal> decimalList) {

        Collections.sort(decimalList);

        int size = decimalList.size();
        BigDecimal median;

        // Check if size of list is even or odd
        if (size % 2 == 0) {
            median = Decimal.divide(Decimal.add(decimalList.get(size / 2), decimalList.get(size / 2 - 1)), Decimal.format5Bit(2));
        } else {
            median = decimalList.get(size / 2);
        }
        return format(median);
    }
}