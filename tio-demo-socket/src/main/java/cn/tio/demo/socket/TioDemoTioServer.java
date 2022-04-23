package cn.tio.demo.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.server.ServerTioConfig;
import org.tio.server.TioServer;

/**
 * tioServer对象
 *
 * @author
 */
@Component
public class TioDemoTioServer extends TioServer {


    public TioDemoTioServer(@Autowired ServerTioConfig serverTioConfig) {
        super(serverTioConfig);
    }
}
