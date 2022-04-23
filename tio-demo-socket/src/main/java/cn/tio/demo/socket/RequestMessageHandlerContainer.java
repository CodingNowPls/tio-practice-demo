package cn.tio.demo.socket;


import com.tio.common.enums.InterfaceNameEnum;
import com.tio.common.socket.base.RequestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author
 */
@Slf4j
@Component
public class RequestMessageHandlerContainer implements InitializingBean {

    /**
     * 消息类型与 MessageHandler 的映射
     */
    private final Map<String, RequestMessageHandler> handlers = new HashMap<>(InterfaceNameEnum.values().length);

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 获得 MessageHandler 处理的消息类
     *
     * @param handler 处理器
     * @return 消息类
     */
    public static Class<? extends RequestMessage> getMessageClass(RequestMessageHandler handler) {
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass().getSuperclass();
        // 此处，是以父类的接口为准
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) {
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            // 遍历 interfaces 数组
            for (Type type : interfaces) {
                // 要求 type 是泛型参数
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), RequestMessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<RequestMessage>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 通过 ApplicationContext 获得所有 MessageHandler Bean
        // 获得所有 MessageHandler Bean
        applicationContext.getBeansOfType(RequestMessageHandler.class).values()
                // 添加到 handlers 中
                .forEach(messageHandler -> handlers.put(messageHandler.getService(), messageHandler));
        log.info("[afterPropertiesSet][接收响应消息处理器数量：{}]", handlers.size());
    }

    /**
     * 获得类型对应的 MessageHandler
     *
     * @param type 类型
     * @return MessageHandler
     */
    public RequestMessageHandler getMessageHandler(String type) {
        RequestMessageHandler handler = handlers.get(type);
        if (handler == null) {
            throw new IllegalArgumentException(String.format("类型(%s) 找不到匹配的 ResponseMessageHandler 处理器", type));
        }
        return handler;
    }

}
