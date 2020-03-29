package com.vf.core.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户工具类
 *
 * @author levin
 * @since 1.0.0
 */
public class UserHolder {
    public static User getUser() {
        return null;
    }

    public static Serializable getUserId() {
        User user = getUser();
        if (Objects.isNull(user)) {
            return null;
        } else {
            return (Serializable) user.getId();
        }
    }
}
