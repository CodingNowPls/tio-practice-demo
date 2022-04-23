package com.tio.common.enums;

import lombok.Getter;

/**
 * 多位多车
 *
 * @author
 */

public enum MorePlateEnum {


    YES(1, "是"),
    NO(0, "否"),


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


    MorePlateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
