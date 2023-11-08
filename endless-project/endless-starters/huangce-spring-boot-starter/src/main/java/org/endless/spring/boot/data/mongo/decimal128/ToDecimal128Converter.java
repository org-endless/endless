package org.endless.spring.boot.data.mongo.decimal128;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

/**
 * ToDecimal128Converter
 * <p>Mongo 写入时将 Decimal 转换为 Decimal128
 * <p>Decimal convert to Decimal128, When Mongo writing.
 * <p>
 * <p>create 2023/5/23 17:03
 * <p>update 2023/9/26 11:52
 *
 * @author Deng Haozhi
 * @see Converter
 * @since 0.0.4
 */
@WritingConverter
public class ToDecimal128Converter implements Converter<BigDecimal, Decimal128> {

    @Override
    public Decimal128 convert(@Nullable BigDecimal source) {
        if (source == null) {
            return null;
        }
        return new Decimal128(source);
    }
}
