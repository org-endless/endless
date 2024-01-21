package org.endless.spring.boot.com.utiliy.object;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Convert all values to String in a Map
 *
 * @author Deng Haozhi
 * @date 2023/4/15 18:59
 * @since 0.0.2
 */
public class ObjectToStringObject {

    public static Object convert(Object object) {

        if (object instanceof String || object instanceof Integer || object instanceof Double || object instanceof Boolean)
            return String.valueOf(object).trim();

        if (object instanceof Map<?, ?>) {
            Map<String, Object> map = keyToString((Map<?, ?>) object);
            map.entrySet().forEach(entry -> entry.setValue(convert(entry.getValue())));
            return map;
        }

        if (object instanceof List<?>) {
            return ((List<?>) object).stream().map(ObjectToStringObject::convert).collect(Collectors.toList());
        }
        return object;
    }

    protected static Map<String, Object> keyToString(Map<?, ?> map) {
        return map.keySet().stream().collect(Collectors.toMap(String::valueOf, map::get, (a, b) -> b));
    }
}
