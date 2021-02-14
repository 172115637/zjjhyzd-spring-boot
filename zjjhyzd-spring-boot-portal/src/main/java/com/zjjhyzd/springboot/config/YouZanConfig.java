package com.zjjhyzd.springboot.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "youzan")
@ConditionalOnProperty(name = {"enable"}, prefix = "youzan", havingValue = "true")
public class YouZanConfig {
    private Boolean enable = false;
    private String clientId;
    private String clientSecret;
    private String grantId;
}
