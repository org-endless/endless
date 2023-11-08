package org.endless.spring.boot.data.mongo.decimal128;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

/**
 * FromDecimal128Converter
 * <p>Mongo 读取时将 Decimal128 转换为 Decimal
 * <p>Decimal128 convert to Decimal, When Mongo reading.
 * <p>
 * <p>create 2023/5/23 17:07
 * <p>update 2023/9/26 11:36
 *
 * @author Deng Haozhi
 * @see Converter
 * @since 0.0.4
 */
@ReadingConverter
public class FromDecimal128Converter implements Converter<Decimal128, BigDecimal> {
    @Override
    public BigDecimal convert(@Nullable Decimal128 source) {
        if (source == null) {
            return null;
        }
        return source.bigDecimalValue();
    }
}
