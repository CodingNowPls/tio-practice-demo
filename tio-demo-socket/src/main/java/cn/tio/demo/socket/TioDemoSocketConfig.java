package cn.tio.demo.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@DependsOn("CommonConfig")
@Configuration("TioDemoSocketConfig")
@ComponentScan(basePackages = {"cn.tio.demo.socket"})
public class TioDemoSocketConfig {
    public TioDemoSocketConfig() {
        log.info("===================socket公共模块加载===================");
    }

}
