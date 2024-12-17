package com.yky.bilibilibackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.yky.bilibilibackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class BilibiliBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilibiliBackendApplication.class, args);
    }

}
