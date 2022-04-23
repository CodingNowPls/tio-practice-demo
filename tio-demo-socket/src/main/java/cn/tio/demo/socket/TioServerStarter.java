package cn.tio.demo.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tio.server.TioServer;

import javax.annotation.PreDestroy;

/**
 * tio 服务启动
 *
 * @author
 */
@Slf4j
@Component
public class TioServerStarter implements CommandLineRunner {

    /**
     * tioServer对象
     */
    @Autowired
    public TioServer tioServer;
    /**
     * 服务器地址
     */
    @Value("${tio.server}")
    private String server;
    /**
     * 监听端口
     */
    @Value("${tio.port}")
    private Integer port;


    @Override
    public void run(String... args) throws Exception {
        tioServer.start(server, port);
    }

    /**
     * 关闭  Server
     */
    @PreDestroy
    public void shutdown() {
        log.info("需要关闭tio服务,暂时不清楚");
    }
}
