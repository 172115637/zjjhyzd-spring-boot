package com.zjjhyzd.springboot;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.zjjhyzd.springboot.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties(value = {AliyunOSSProperties.class, AliyunSMSProperties.class, UploadProperties.class, YouZanProperties.class, WxMaProperties.class})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
