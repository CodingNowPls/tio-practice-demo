package com.tio.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration("CommonConfig")
@ComponentScan(basePackages = {"cn.tio.common"})
public class CommonConfig {
    public CommonConfig() {
        log.info("===================common公共模块加载===================");
    }

}
