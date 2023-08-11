package org.endless.erp.share.constant;

import java.math.BigDecimal;

/**
 * ConstantResource
 *
 * @author Deng Haozhi
 * @date 2022/11/18 17:25
 * @since 0.0.1
 */
public class ConstantResource {
    public static final Integer PAGE_SIZE = 1000;
    public static final Integer CURSOR_BATCH_SIZE = 100;

    public static final String REGION_ID = "10000002";

    public static final BigDecimal MINUTES_PER_DAY = new BigDecimal("1440");

    public static final BigDecimal SECONDS_PER_DAY = new BigDecimal("86400");

    private static final String EVE_ITEM_FILE = "data/typeIDs.yaml";
}
