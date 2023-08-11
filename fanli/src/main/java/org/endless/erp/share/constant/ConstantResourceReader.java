package org.endless.erp.share.constant;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * ConstantResourceReader
 *
 * @author Deng Haozhi
 * @date 2022/11/18 15:02
 * @since 0.0.1
 */
@Component
@Getter
@ToString
@PropertySource(value = {"file:config/constant.conf"})
public class ConstantResourceReader {

    public static String ID_REGULAR;
    public static String DATE_REGULAR;
}
