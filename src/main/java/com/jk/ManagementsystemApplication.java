package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.jk.dao")
@EnableFeignClients//可以去注册中心调用其他服务
@EnableDiscoveryClient//可以去注册中心去注册
@SpringBootApplication
public class ManagementsystemApplication{

    public static void main(String[] args) {
        SpringApplication.run(ManagementsystemApplication.class, args);
    }

}
