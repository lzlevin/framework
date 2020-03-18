package com.vin.framework.admin.constant;

import com.vin.framework.core.common.KeyValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 证件号
 *
 * @author levin
 * @since 1.0.0
 */
@AllArgsConstructor
public enum CertificateType implements KeyValuable<String, String> {
    SFZ("SFZ", "身份证");


    @Getter
    private String key;
    @Getter
    private String value;
}
