package com.tio.common.enums;

import lombok.Getter;

/**
 * 车收费类型
 */
public enum CarFeeTypeEnum {

    FREE(1,"永久免费"),

    MONTH(2,"月租"),

    TEMP(2,"临时"),
            ;

    /**
     * code
     */
    @Getter
    private final int code;
    /**
     * 描述
     */
    @Getter
    private final String desc;


    CarFeeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
