package cn.tio.demo.socket;


import com.tio.common.socket.base.RequestMessage;
import com.tio.common.validator.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Set;

/**
 * 消息处理器
 *
 * @author
 */
public interface RequestMessageHandler<T extends RequestMessage> {

    Logger log = LoggerFactory.getLogger(RequestMessageHandler.class);

    /**
     * @return 类型
     */
    String getService();

    /**
     * 执行处理消息
     *
     * @param
     * @param message 消息
     */
    void execute(ChannelContext cc, T message);

    /**
     * 参数校验
     *
     * @param message
     * @return
     */
    default Set<ConstraintViolation<T>> validParam(T message) {
        try {
            Set<ConstraintViolation<T>> constraintViolationSet = ValidatorUtils.validateFast(message);
            if (constraintViolationSet.isEmpty()) {
                return Collections.emptySet();
            }
            return constraintViolationSet;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 参数校验
     *
     * @param message
     * @param groups
     * @return
     */
    default Set<ConstraintViolation<T>> validParam(T message, Class<?>... groups) {
        try {
            Set<ConstraintViolation<T>> constraintViolationSet = ValidatorUtils.validateFast(message, groups);
            if (constraintViolationSet.isEmpty()) {
                return Collections.emptySet();
            }
            return constraintViolationSet;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
