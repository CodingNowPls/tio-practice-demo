package com.tio.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * spring 上下文工具类
 *
 * @author
 */
@Component("SpringContextUtil")
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 通过名称和Class获取对象实例
     *
     * @param beanName bean的名称
     * @param <T>      泛型
     * @return 对象实例
     */
    public static <T> T getBean(String beanName) {
        try {
            if (beanName == null) {
                return null;
            }
            return (T) applicationContext.getBean(beanName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过名称和Class获取对象实例
     *
     * @param beanName bean的名称
     * @param <T>      泛型
     * @return 对象实例
     */
    public static <T> T getBeanOrNull(String beanName) {
        try {
            if (beanName == null) {
                return null;
            }
            return (T) applicationContext.getBean(beanName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过名称和Class获取对象实例
     *
     * @param requiredType bean的Class
     * @param <T>          泛型
     * @return 对象实例
     */
    public static <T> T getBean(Class<T> requiredType) {
        try {
            if (requiredType == null) {
                return null;
            }
            return applicationContext.getBean(requiredType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过名称和Class获取对象实例
     *
     * @param requiredType bean的Class
     * @param <T>          泛型
     * @return 对象实例
     */
    public static <T> T getBeanOrNull(Class<T> requiredType) {
        try {
            if (requiredType == null) {
                return null;
            }
            return applicationContext.getBean(requiredType);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过名称和Class获取对象实例
     *
     * @param beanName     bean的名称
     * @param requiredType bean的Class
     * @param <T>          泛型
     * @return 对象实例
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        try {
            if (requiredType == null) {
                return null;
            }
            return applicationContext.getBean(beanName, requiredType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过名称和Class获取对象实例
     *
     * @param beanName     bean的名称
     * @param requiredType bean的Class
     * @param <T>          泛型
     * @return 对象实例
     */
    public static <T> T getBeanOrNull(String beanName, Class<T> requiredType) {
        try {
            if (requiredType == null) {
                return null;
            }
            return applicationContext.getBean(beanName, requiredType);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过固定的class 获取一组实现类
     *
     * @param requiredType 基类Class
     * @param <T>          泛型
     * @return map
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) {
        try {
            if (requiredType == null) {
                return null;
            }
            return applicationContext.getBeansOfType(requiredType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取注解下的所有对象实例
     *
     * @param annotationType 注解的Class
     * @return 对象实例
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        if (annotationType == null) {
            return null;
        }
        return applicationContext.getBeansWithAnnotation(annotationType);
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }
}
