package cn.tio.demo.biz.heartbeat;


import cn.tio.demo.socket.RequestMessageHandler;
import cn.tio.demo.socket.SocketUtil;
import com.tio.common.enums.InterfaceNameEnum;
import com.tio.common.enums.ResultCodeEnum;
import com.tio.common.socket.request.HeartBeatRequestMessage;
import com.tio.common.socket.response.HeartbeatResponseMessage;
import com.tio.common.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;

import javax.validation.ConstraintViolation;
import java.util.Objects;
import java.util.Set;

/**
 * 服务器心跳响应结果
 *
 * @author
 */
@Slf4j
@Component
public class HeartBeatRequestMessageHandler implements RequestMessageHandler<HeartBeatRequestMessage> {

    @Value(("${parking.logPrint:false}"))
    private Boolean logPrint;

    @Override
    public String getService() {
        return InterfaceNameEnum.HEART_BEAT.getName();
    }


    @Override
    public void execute(ChannelContext cc, HeartBeatRequestMessage message) {
        if (logPrint) {
            log.info("接收到的客户端心跳结果:{}", message);
        }
        HeartbeatResponseMessage responseMessage = new HeartbeatResponseMessage();
        responseMessage.setService(InterfaceNameEnum.HEART_BEAT.getName());
        Set<ConstraintViolation<HeartBeatRequestMessage>> constraintViolationSet = validParam(message);
        if (Objects.isNull(constraintViolationSet) || !constraintViolationSet.isEmpty()) {
            responseMessage.setResultCode(ResultCodeEnum.FAIL_2.getCode());
            responseMessage.setMessage("参数不能为空");
            SocketUtil.sendMsg(cc, JacksonUtils.toJson(responseMessage));
            return;
        }
        SocketUtil.connect = cc;
        responseMessage.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        responseMessage.setMessage("在线");
        SocketUtil.sendMsg(cc, JacksonUtils.toJson(responseMessage));
    }


}
