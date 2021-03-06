package com.zjjhyzd.springboot.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /**
     * 阿里云SMS客户端
     *
     * @param config
     * @return
     */
    @Bean
    @ConditionalOnBean(AliyunSMSProperties.class)
    public IAcsClient iAcsClient(AliyunSMSProperties config) {
        return new DefaultAcsClient(DefaultProfile.getProfile(config.getRegionId(), config.getAccessKeyId(), config.getAccessKeySecret()));
    }

    /**
     * 阿里云OSS客户端
     *
     * @param config
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnBean(AliyunOSSProperties.class)
    public OSS ossClient(AliyunOSSProperties config) {
        return new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
    }

    /**
     * 有赞API客户端
     *
     * @return
     */
    @Bean
    @ConditionalOnBean(YouZanProperties.class)
    public YouZanClient youZanClient() {
        return new DefaultYZClient();
    }

    /**
     * 有赞 token 参数
     *
     * @param config
     * @return
     * @throws SDKException
     */
    @Bean
    @ConditionalOnBean(YouZanProperties.class)
    public TokenParameter tokenParameter(YouZanProperties config) throws SDKException {
        return TokenParameter.self()
                .clientId(config.getClientId())
                .clientSecret(config.getClientSecret())
                .grantId(config.getGrantId())
                .refresh(true)
                .build();
    }

    /**
     * 有赞token
     * 此处应该为定时器拉取
     *
     * @param client
     * @param parameter
     * @return
     * @throws SDKException
     */
    @Bean
    @ConditionalOnBean(YouZanProperties.class)
    public OAuthToken oAuthToken(YouZanClient client, TokenParameter parameter) throws SDKException {
        return client.getOAuthToken(parameter);
    }

}
