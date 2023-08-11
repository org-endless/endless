package org.endless.erp.game.eve.share.regular;

import org.endless.erp.share.pattern.Regular;

import java.util.regex.Pattern;

/**
 * GameEveRegular
 *
 * @author Deng Haozhi
 * @date 2023/5/19 16:27
 * @since 0.0.3
 */
public class GameEveRegular extends Regular {

    // EVE
    public static final String EVE_YAML = "^\\d{1,10}:$";

    public static final int EVE_YAML_FLAG = Pattern.MULTILINE;

}
