package org.endless.erp.share.mongo.config;

import java.math.BigDecimal;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.Nullable;

/**
 * ToDecimal128Converter
 *
 * @author Deng Haozhi
 * @date 2023/5/23 17:03
 * @since 0.0.3
 */
@WritingConverter
public class ToDecimal128Converter implements Converter<BigDecimal, Decimal128> {

  @Override
  public Decimal128 convert(@Nullable BigDecimal source) {
    if (source == null) return null;
    return new Decimal128(source);
  }
}
