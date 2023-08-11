package org.endless.data.mongo.config.decimal128;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

/**
 * FromDecimal128Converter
 *
 * @author Deng Haozhi
 * @date 2023/5/23 17:07
 * @since 0.0.3
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
