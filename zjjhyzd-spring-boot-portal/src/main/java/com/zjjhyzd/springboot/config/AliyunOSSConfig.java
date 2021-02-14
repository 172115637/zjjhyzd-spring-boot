package com.zjjhyzd.springboot.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
@ConditionalOnProperty(name = {"enable"}, prefix = "aliyun.oss", havingValue = "true")
public class AliyunOSSConfig {
    private Boolean enable = false;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
}
