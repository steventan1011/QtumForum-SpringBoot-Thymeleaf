package com.dcits.qtumforum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author T-bk
 * @version 2.0
 * @date 2020/7/22 14:01
 */

@SpringBootApplication
@MapperScan("com.dcits.qtumforum.mapper")
@EnableScheduling
public class QtumfoumApplication {
    public static void main(String[] args) {

        SpringApplication.run(QtumfoumApplication.class, args);
    }
}
