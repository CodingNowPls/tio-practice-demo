package cn.tio.demo.biz.jobs;


import cn.tio.demo.socket.SocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 业务定时接口
 *
 * @author
 */
@Slf4j
@Component
public class BizRequestJob {
    @Resource
    protected ThreadPoolTaskExecutor parkingServerTaskExecutor;

    /**
     *
     */
    @Scheduled(cron = "${tio.corn:0/5 * * * * ?}")
    public void process() {
        log.info("job 请求");
        if (Objects.nonNull(SocketUtil.connect) && !SocketUtil.connect.isClosed) {
           log.info("job 请求");
            return;
        }
        log.info("无心跳,不下发请求");
    }

}
