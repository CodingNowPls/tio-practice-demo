package com.tio.common.enums;

import lombok.Getter;

/**
 * 支付场景
 *
 * @author
 */

public enum PaySceneEnum {

    PARKING_PREPAID(0, "场内预付"),
    PARKING_EXPORT(1, "出口直付"),
    NO_LICENSE_PLATE_BALANCE(2, "无牌车请求结算"),

    ;

    /**
     * 支付场景 code
     */
    @Getter
    private final int code;
    /**
     * 支付场景描述
     */
    @Getter
    private final String desc;


    PaySceneEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
