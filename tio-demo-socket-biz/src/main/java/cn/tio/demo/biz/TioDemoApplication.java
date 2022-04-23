package cn.tio.demo.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动入口
 *
 * @author
 */
@EnableScheduling
@SpringBootApplication
@DependsOn("TioDemoSocketConfig")

public class TioDemoApplication {

    /**
     * 启动程序入口
     */
    public static void main(String[] args) {
        SpringApplication.run(TioDemoApplication.class, args);
    }
}