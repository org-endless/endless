package org.endless.erp.share.util.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Deng Haozhi
 * @date 2023/5/10 23:19
 * @since 0.0.2
 */
public class ListSplitter {

    public static <T> List<List<T>> sub(List<T> list, int splitSize) {

        if (list == null || list.isEmpty() || splitSize < 1) {
            return Collections.emptyList();
        }

        List<List<T>> split = new ArrayList<>();

        for (int i = 0; i < (list.size() + splitSize - 1) / splitSize; i++) {
            List<T> subList = list.subList(i * splitSize, Math.min((i + 1) * splitSize, list.size()));
            split.add(subList);
        }
        return split;
    }
}