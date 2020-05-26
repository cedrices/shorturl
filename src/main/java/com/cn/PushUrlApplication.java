package com.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.Mapping;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = "com.cn.repository")
public class PushUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushUrlApplication.class,args);
    }
}
