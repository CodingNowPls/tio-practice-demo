package com.tio.common.enums;

import com.tio.common.validator.Group;
import lombok.Getter;

import java.util.Objects;

/**
 * 操作类型
 *
 * @author
 */

public enum OperateTypeEnum {

    ADD(1, "添加", Group.Add.class),
    UPDATE(2, "修改", Group.Update.class),
    DEL(3, "删除", Group.Del.class),

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

    @Getter
    private final Class clazz;


    OperateTypeEnum(int code, String desc, Class clazz) {
        this.code = code;
        this.desc = desc;
        this.clazz = clazz;
    }

    public static OperateTypeEnum getOperateType(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        OperateTypeEnum[] values = OperateTypeEnum.values();
        for (OperateTypeEnum type : values) {
            if (type.getCode() == code.intValue()) {
                return type;
            }
        }
        return null;
    }


}
