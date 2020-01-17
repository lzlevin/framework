package com.vin.framework.core.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * 用户工具类
 *
 * @author levin
 * @since 1.0.0
 */
public class UserHolder {
    public static User getUser() {
        return new User() {
            @Override
            public Integer getSeq() {
                return null;
            }

            @Override
            public Serializable getId() {
                return UUID.randomUUID().toString();
            }

            @Override
            public Integer getDeleted() {
                return null;
            }

            @Override
            public String getCreateBy() {
                return null;
            }

            @Override
            public LocalDateTime getCreateTime() {
                return null;
            }

            @Override
            public String getModifyBy() {
                return null;
            }

            @Override
            public LocalDateTime getModifyTime() {
                return null;
            }
        };
    }

    public static Serializable getUserId() {
        User user = getUser();
        if (Objects.isNull(user)) {
            return null;
        } else {
            return user.getId();
        }
    }
}
