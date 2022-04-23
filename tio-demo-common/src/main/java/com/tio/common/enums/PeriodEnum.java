package com.tio.common.enums;

import lombok.Getter;

/**
 * 收费周期
 *
 * @author
 */

public enum PeriodEnum {


    MONTH("月"),
    QUARTER("季"),
    HALF_YEAR("半年"),
    YEAR("年"),
    ;

    /**
     * 周期
     */
    @Getter
    private final String name;

    PeriodEnum(String name) {
        this.name = name;
    }


}
