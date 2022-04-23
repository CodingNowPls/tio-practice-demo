package cn.tio.demo.socket;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tio.server.ServerTioConfig;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

/**
 * 一组连接共用的上下文对象
 *
 * @author
 */
@Component
public class TioDemoTioConfig extends ServerTioConfig implements InitializingBean {

    @Value("${tio.configName}")
    private String configName;

    @Value("${tio.timeout}")
    private Integer timeout;

    public TioDemoTioConfig(@Autowired ServerAioHandler serverAioHandler, @Autowired ServerAioListener serverAioListener) {
        super(serverAioHandler, serverAioListener);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //设置config的名称
        this.setName(configName);
        this.setHeartbeatTimeout(timeout);
    }
}
