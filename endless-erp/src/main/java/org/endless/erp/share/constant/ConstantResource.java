package org.endless.erp.share.constant;

import java.math.BigDecimal;
import java.util.Queue;

/**
 * ConstantResource
 *
 * @author Deng Haozhi
 * @date 2022/11/18 17:25
 * @since 0.0.1
 */
public class ConstantResource {
    public final static Integer PAGE_SIZE = 1000;

    public final static String REGION_ID = "10000002";

    public final static BigDecimal MINUTES_PER_DAY = new BigDecimal("1440");

    public final static BigDecimal SECONDS_PER_DAY = new BigDecimal("86400");

    private final static String EVE_ITEM_FILE = "data/typeIDs.yaml";
}