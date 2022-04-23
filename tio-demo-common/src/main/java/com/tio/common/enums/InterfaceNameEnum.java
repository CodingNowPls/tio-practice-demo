package com.tio.common.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口地址 枚举
 *
 * @author
 */

public enum InterfaceNameEnum {

    CHECK_KEY("checkKey", "客户端认证"),
    HEART_BEAT("heartbeat", "心跳"),

    ;

    /**
     * 接口地址名称
     */
    @Getter
    private final String name;
    /**
     * 接口地址描述
     */
    @Getter
    private final String desc;

    InterfaceNameEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static Map<String, InterfaceNameEnum> map = new HashMap<>(InterfaceNameEnum.values().length);
    static {
        InterfaceNameEnum[] values = InterfaceNameEnum.values();
        for (InterfaceNameEnum value : values) {
            map.put(value.name, value);
        }

    }

    /**
     * 判断接口是否存在
     *
     * @param serviceName
     * @return
     */
    public static boolean interfaceNameIsExist(String serviceName) {
        return map.containsKey(serviceName);
    }

}
