package com.tio.common.enums;

import lombok.Getter;

/**
 * 车辆类型
 *
 * @author
 */

public enum CarTypeEnum {

    SMALL_CAR(0, "小车"),
    CART(1, "大车"),


    ;

    /**
     * 车辆类型 code
     */
    @Getter
    private final int code;
    /**
     * 车辆类型描述
     */
    @Getter
    private final String desc;


    CarTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
