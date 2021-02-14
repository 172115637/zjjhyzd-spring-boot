package com.zjjhyzd.springboot.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
@ConditionalOnProperty(name = {"enable"}, prefix = "aliyun.sms", havingValue = "true")
public class AliyunSMSConfig {
    private Boolean enable = false;
    private String regionId;
    private String accessKeyId;
    private String accessKeySecret;
}
