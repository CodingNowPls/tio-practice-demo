package cn.tio.demo.biz.auth;

import cn.tio.demo.socket.RequestMessageHandler;
import cn.tio.demo.socket.SocketUtil;
import com.tio.common.enums.InterfaceNameEnum;
import com.tio.common.enums.ResultCodeEnum;
import com.tio.common.socket.request.AuthRequestMessage;
import com.tio.common.socket.response.AuthResponseMessage;
import com.tio.common.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

import javax.validation.ConstraintViolation;
import java.util.Objects;
import java.util.Set;

/**
 * 客户端认证请求
 *
 * @author
 */
@Slf4j
@Component
public class AuthRequestMessageHandler implements RequestMessageHandler<AuthRequestMessage> {

    @Value("${parking.parkId}")
    private String parkId;

    @Value("${parking.parkKey}")
    private String parkKey;

    @Value(("${parking.logPrint:false}"))
    private Boolean logPrint;

    @Override
    public String getService() {
        return InterfaceNameEnum.CHECK_KEY.getName();
    }

    @Override
    public void execute(ChannelContext cc, AuthRequestMessage message) {
        if (logPrint) {
            log.info("客户端认证信息:{}", message);
        }
        AuthResponseMessage authResponseMessage = new AuthResponseMessage();
        authResponseMessage.setService(InterfaceNameEnum.CHECK_KEY.getName());
        Set<ConstraintViolation<AuthRequestMessage>> constraintViolationSet = validParam(message);
        if (Objects.isNull(constraintViolationSet) || !constraintViolationSet.isEmpty()) {
            authResponseMessage.setResultCode(ResultCodeEnum.FAIL_2.getCode());
            authResponseMessage.setMessage("参数不能为空");
            SocketUtil.sendMsg(cc, JacksonUtils.toJson(authResponseMessage));
            return;
        }

        String reParkId = message.getParkId();
        String reParkKey = message.getParkKey();
        if (!reParkId.equals(parkId) || !reParkKey.equals(parkKey)) {
            authResponseMessage.setResultCode(ResultCodeEnum.FAIL_1.getCode());
            authResponseMessage.setMessage("认证失败，无此车场");
            SocketUtil.sendMsg(cc, JacksonUtils.toJson(authResponseMessage));
            return;
        }
        //绑定用户
        Tio.bindToken(cc, parkId);
        authResponseMessage.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        authResponseMessage.setMessage("认证成功");
        SocketUtil.sendMsg(cc, JacksonUtils.toJson(authResponseMessage));

    }


}
