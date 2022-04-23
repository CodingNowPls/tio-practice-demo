package com.tio.common.enums;

import lombok.Getter;

/**
 * 收费类型
 *
 * @author
 */
public enum PayTypeEnum {

    MONTH(0, "月租"),
    CHARGE(1, "充值"),


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


    PayTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
