package com.tio.common.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * json工具类
 *
 * @author
 */
@Slf4j
public class JacksonUtils {
    /**
     * 最终操作对象
     **/
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /** 初始化的一些设置 **/
    static {
        // 默认非空不输出
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //设置可用单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置时间的转换格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    private JacksonUtils() {/** 私有构造方法，不可以被实例化 **/}

    /**
     * 对象转json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("转换异常:对象转json", e.getMessage());
        }
        return null;
    }

    /**
     * json字符串转对象
     *
     * @param json        json字符串
     * @param targetClass 转换对象的cladd
     * @param <T>         返回的对象范型
     * @return 返回转换后的对象
     */
    public static <T> T toBean(String json, Class targetClass, Class... elementClasses) {
        try {
            if (json == null || targetClass == null) {
                return null;
            }
            if (elementClasses == null || elementClasses.length == 0) {
                return (T) objectMapper.readValue(json, targetClass);
            } else {
                return objectMapper.readValue(json, getCollectionType(targetClass, elementClasses));
            }
        } catch (IOException e) {
            log.error("转换异常:json转对象", e.getMessage());
        }
        return null;
    }

    /**
     * json字符串转对象
     *
     * @param json            json字符串
     * @param collectionClass 泛型的Collection
     * @param type            泛型type
     * @param <T>             返回的对象范型
     * @return 返回转换后的对象
     */
    public static <T> T toBean(String json, Class collectionClass, Type type) {
        Class[] cla = null;
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            //获取参数的泛型列表
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            cla = new Class[actualTypeArguments.length];
            for (int i = 0, len = actualTypeArguments.length; i < len; i++) {
                cla[i] = (Class) actualTypeArguments[i];
            }
            return toBean(json, collectionClass, cla);
        } else {
            if (type == null || collectionClass.equals(type)) {
                return toBean(json, collectionClass);
            } else {
                return toBean(json, collectionClass, new Class[]{(Class) type});
            }
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
