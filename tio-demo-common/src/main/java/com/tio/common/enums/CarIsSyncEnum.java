package com.tio.common.enums;

import lombok.Getter;

/**
 * 车信息是否同步过
 */
public enum CarIsSyncEnum {

    SYNCED(1, "已同步"),

    UN_SYNC(0, "未同步"),


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


    CarIsSyncEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
