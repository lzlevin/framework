package com.vf.utils.id;

import java.util.UUID;

/**
 * id生成器
 *
 * @author levin
 * @since 1.0.0
 */
public class IdUtils {

    public static String getStrId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
