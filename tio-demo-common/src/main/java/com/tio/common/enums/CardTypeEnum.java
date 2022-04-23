package com.tio.common.enums;

import lombok.Getter;

/**
 * 车牌类型
 *
 * @author
 */

public enum CardTypeEnum {

    MONTHLY_RENT_CAR(1, "月租车"),
    RECHARGE_RENT_CAR(2, "充租车"),


    ;

    /**
     * 车牌类型	 code
     */
    @Getter
    private final int code;
    /**
     * 车牌类型	描述
     */
    @Getter
    private final String desc;


    CardTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
