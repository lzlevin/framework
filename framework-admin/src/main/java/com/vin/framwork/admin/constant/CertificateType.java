package com.vin.framwork.admin.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 证件号
 *
 * @author levin
 * @since 1.0.0
 */
@AllArgsConstructor
public enum CertificateType implements UserStatus {
    SFZ("SFZ", "身份证");


    @Getter
    private String key;
    @Getter
    private String message;
}
