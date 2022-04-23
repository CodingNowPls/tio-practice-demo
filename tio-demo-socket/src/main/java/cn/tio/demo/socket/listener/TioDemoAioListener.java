package cn.tio.demo.socket.listener;

import com.tio.common.socket.bean.AuthMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;


/**
 * tio listener
 *
 * @author
 */
@Slf4j
@Component
public class TioDemoAioListener implements ServerAioListener {

    private AuthMessage authRequestMessage;




    /**
     * 建链后触发本方法，注：建链不一定成功，需要关注参数isConnected
     *
     * @param cc
     * @param isConnected 是否连接成功,true:表示连接成功，false:表示连接失败
     * @param isReconnect 是否是重连, true: 表示这是重新连接，false: 表示这是第一次连接
     * @throws Exception
     */
    @Override
    public void onAfterConnected(ChannelContext cc, boolean isConnected, boolean isReconnect) throws Exception {
        log.info("server onAfterConnected");
        // 获得  MessageHandler 处理器 的消息类

    }

    /**
     * 原方法名：onAfterDecoded
     * 解码成功后触发本方法
     *
     * @param cc
     * @param packet
     * @param packetSize
     * @throws Exception
     */
    @Override
    public void onAfterDecoded(ChannelContext cc, Packet packet, int packetSize) throws Exception {
        log.info("server onAfterDecoded");
    }

    /**
     * 接收到TCP层传过来的数据后
     *
     * @param cc
     * @param receivedBytes 本次接收了多少字节
     * @throws Exception
     */

    @Override
    public void onAfterReceivedBytes(ChannelContext cc, int receivedBytes) throws Exception {
        log.info("server onAfterReceivedBytes");
    }

    /**
     * 消息包发送之后触发本方法
     *
     * @param cc
     * @param packet
     * @param isSentSuccess true:发送成功，false:发送失败
     * @throws Exception
     */
    @Override
    public void onAfterSent(ChannelContext cc, Packet packet, boolean isSentSuccess) throws Exception {
        log.info("server onAfterSent");
    }

    /**
     * 处理一个消息包后
     *
     * @param cc
     * @param packet
     * @param cost   本次处理消息耗时，单位：毫秒
     * @throws Exception
     */
    @Override
    public void onAfterHandled(ChannelContext cc, Packet packet, long cost) throws Exception {
        log.info("server onAfterHandled");
    }

    /**
     * 连接关闭前触发本方法
     *
     * @param cc        the channelcontext
     * @param throwable the throwable 有可能为空
     * @param remark    the remark 有可能为空
     * @param isRemove
     * @throws Exception
     */
    @Override
    public void onBeforeClose(ChannelContext cc, Throwable throwable, String remark, boolean isRemove) throws Exception {
        log.info("server onBeforeClose");
        log.info("关闭后清除认证信息");
        Tio.unbindToken(cc);
    }

    /**
     * @param cc
     * @param interval              已经多久没有收发消息了，单位：毫秒
     * @param heartbeatTimeoutCount 心跳超时次数，第一次超时此值是1，以此类推。此值被保存在：channelContext.stat.heartbeatTimeoutCount
     * @return 返回true，那么服务器则不关闭此连接；返回false，服务器将按心跳超时关闭该连接
     */
    @Override
    public boolean onHeartbeatTimeout(ChannelContext cc,
                                      Long interval,
                                      int heartbeatTimeoutCount) {
        Tio.unbindToken(cc);
        return false;
    }
}