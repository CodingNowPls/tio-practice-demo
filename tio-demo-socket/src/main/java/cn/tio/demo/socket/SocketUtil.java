package cn.tio.demo.socket;

import cn.tio.demo.socket.packet.TioDemoPacket;
import com.tio.common.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

/**
 * @author
 */
public class SocketUtil {
    private static final Logger log = LoggerFactory.getLogger(SocketUtil.class);
    public static ChannelContext connect = null;

    public static void sendMsg(ChannelContext cc, String resp) {
        try {
            String json = JacksonUtils.toJson(resp);
            log.info("发送的json:{}", json);
            TioDemoPacket packet = new TioDemoPacket();
            packet.setBody(json.getBytes());
            //发送心跳信息
            Tio.send(cc, packet);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}