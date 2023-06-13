package org.endless.erp.share.util.object;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import org.endless.erp.share.util.decimal.Decimal;

/**
 * @author Deng Haozhi
 * @date 2023/4/20 17:48
 * @since 0.0.2
 */
public class ObjectToMongoObject {

    public static Object convert(Object object) {

        if (object instanceof String || object instanceof Integer || object instanceof Double || object instanceof Short || object instanceof Date)
            return String.valueOf(object).trim();

        if (object instanceof BigDecimal)
            return Decimal.format(object);

        if (object instanceof Map<?, ?>) {
            var map = keyToString((Map<?, ?>) object);
            map.entrySet().forEach(entry -> entry.setValue(convert(entry.getValue())));
            return map;
        }

        if (object instanceof List<?>) {
            return ((List<?>) object).stream().map(ObjectToMongoObject::convert).collect(Collectors.toList());
        }

        if (object instanceof Boolean) return object;

        return object;
    }

    protected static Map<String, Object> keyToString(Map<?, ?> map) {
        Map<String, Object> result = new HashMap<>();
        for (Object key : map.keySet()) {
            // handler eve typeID
            if (key.equals("typeID")) {
                result.put("itemId", map.get(key));
                continue;
            }
            result.put(String.valueOf(key), map.get(key));
        }
        return result;
    }
}
