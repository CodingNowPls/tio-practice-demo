package com.tio.common.enums;

import lombok.Getter;

/**
 * 车的状态   0:待审核,1:已审核，2:拉黑
 *
 * @author
 */

public enum CarStatusEnum {

    WAIT_AUDIT(0, "待审核"),
    AUDITED(1, "已审核"),
    BLACKED(2, "拉黑"),


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


    CarStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
