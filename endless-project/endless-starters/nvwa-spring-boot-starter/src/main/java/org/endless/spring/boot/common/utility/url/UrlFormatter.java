package org.endless.spring.boot.common.utility.url;

import java.util.Map;

/**
 * UrlFormatter
 *
 * @author Deng Haozhi
 * @date 2023/4/16 23:57
 * @since 0.0.2
 */
public class UrlFormatter {

    public static String para(Map<String, String> paraMap) {

        if (paraMap.isEmpty()) return null;

        StringBuilder paras = new StringBuilder("?");
        for (Map.Entry<String, String> para : paraMap.entrySet())
            paras.append(para.getKey()).append("={").append(para.getKey()).append("}&");

        return paras.substring(0, paras.length() - 1);
    }
}
