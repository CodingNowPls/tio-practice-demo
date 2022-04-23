package com.tio.common.enums;

import lombok.Getter;

/**
 * 单双日限行 0不限行，1单日限行，2双日限行
 *
 * @author
 */

public enum LimitDayTypeEnum {

    NO_PASSING(0, "不限行"),
    ODD_LIMIT_PASSING(1, "单日限行"),
    EVEN_LIMIT_PASSING(2, "双日限行"),

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


    LimitDayTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
