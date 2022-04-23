package cn.tio.demo.socket;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.tio.demo.socket.packet.TioDemoPacket;
import com.tio.common.enums.InterfaceNameEnum;
import com.tio.common.socket.base.RequestMessage;
import com.tio.common.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import javax.annotation.Resource;
import java.nio.ByteBuffer;

/**
 * handler, 包括编码、解码、消息处理
 *
 * @author
 */
@Slf4j
@Component
public class TioDemoAioHandler implements ServerAioHandler {

    @Resource
    protected ThreadPoolTaskExecutor tioDemoTaskExecutor;

    @Autowired
    private RequestMessageHandlerContainer requestMessageHandlerContainer;

    @Value("${parking.parkId}")
    private String parkId;

    @Value(("${parking.logPrint:false}"))
    private Boolean logPrint;


    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     * 根据ByteBuffer解码成业务需要的Packet对象.
     * 如果收到的数据不全，导致解码失败，请返回null，在下次消息来时框架层会自动续上前面的收到的数据
     *
     * @param buffer         参与本次希望解码的ByteBuffer
     * @param limit          ByteBuffer的limit
     * @param position       ByteBuffer的position，不一定是0哦
     * @param readableLength ByteBuffer参与本次解码的有效数据（= limit - position）
     * @param channelContext
     * @return
     * @throws AioDecodeException
     */

    @Override
    public TioDemoPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        if (buffer.remaining() < TioDemoPacket.HEADER_LENGTH) {
            return null;
        } else {
            TioDemoPacket imPacket = new TioDemoPacket();
            if (buffer.remaining() < TioDemoPacket.HEADER_LENGTH) {
                return null;
            } else {
                byte[] bytes = new byte[limit];
                buffer.get(bytes);
                imPacket.setBody(bytes);
                return imPacket;
            }
        }
    }

    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
        TioDemoPacket serverPacket = (TioDemoPacket) packet;
        byte[] body = serverPacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }
        //bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
        int allLen = bodyLen;
        //创建一个新的bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        //设置字节序
        buffer.order(tioConfig.getByteOrder());
        //写入消息头----消息头的内容就是消息体的长度
        //写入消息体
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }

    /**
     * 处理消息
     */
    @Override
    public void handler(Packet packet, ChannelContext cc) throws Exception {
        TioDemoPacket packingPacket = (TioDemoPacket) packet;
        byte[] body = packingPacket.getBody();
        if (body != null) {
            String reqJson = new String(body);
            if (logPrint) {
                log.info("接收到服务器的请求信息:{},{},收到消息：{}", cc.getId(), cc.getToken(), reqJson);
            }
            if (!JSONUtil.isTypeJSON(reqJson)) {
                log.error("参数不是json");
                return;
            }
            JSONObject jb = JSONUtil.parseObj(reqJson);
            String service = jb.getStr("service");
            if (StrUtil.isEmpty(service)) {
                log.error("接口参数缺失");
                return;
            }
            if (!InterfaceNameEnum.interfaceNameIsExist(service)) {
                log.error("接口未接入");
                return;
            }
            RequestMessageHandler requestMessageHandler = requestMessageHandlerContainer.getMessageHandler(service);
            // 获得  MessageHandler 处理器 的消息类
            Class<? extends RequestMessage> messageClass = RequestMessageHandlerContainer.getMessageClass(requestMessageHandler);
            // 解析消息
            RequestMessage requestMessage = JacksonUtils.toBean(reqJson, messageClass);
            //线程池执行
            tioDemoTaskExecutor.submit(() -> {
                //先判断是否是认证接口
                if (InterfaceNameEnum.CHECK_KEY.getName().equals(service)) {
                    requestMessageHandler.execute(cc, requestMessage);
                    return;
                }
                String token = cc.getToken();
                if (StrUtil.isEmpty(token)) {
                    log.error("调用的接口未经认证,不能调用:{}", service);
                    Tio.close(cc, token);
                    return;
                }
                requestMessageHandler.execute(cc, requestMessage);

            });
            return;
        }
    }


}