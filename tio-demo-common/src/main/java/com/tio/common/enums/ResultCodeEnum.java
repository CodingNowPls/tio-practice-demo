package com.tio.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * 返回结果
 *
 * @author
 */

public enum ResultCodeEnum {

    SUCCESS(0),
    FAIL_1(1),
    FAIL_2(2),
    FAIL_3(3),
    FAIL_4(4),
    FAIL_5(5),
    FAIL_6(6),


    ;

    /**
     * 状态码
     */
    @Getter
    private final int code;


    ResultCodeEnum(int code) {
        this.code = code;
    }


    public static ResultCodeEnum getResultCode(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        ResultCodeEnum[] values = ResultCodeEnum.values();
        for (ResultCodeEnum type : values) {
            if (type.getCode() == code.intValue()) {
                return type;
            }
        }
        return null;
    }
}
