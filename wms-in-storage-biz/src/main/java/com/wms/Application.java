package com.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: puck
 * @date: 2020 10:05
 * @describe:
 * @vision
 */
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(
        basePackages = {"com.wms"}
)
@MapperScan("com.wms.mapper")
@EnableDiscoveryClient
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
        System.out.println("succeed!");
    }
}

