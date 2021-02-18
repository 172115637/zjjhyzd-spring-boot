package com.zjjhyzd.springboot.scheduled;

import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import com.zjjhyzd.springboot.config.YouZanProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(YouZanProperties.class)
public class YouZanScheduled {

    @Autowired
    OAuthToken oAuthToken;

    @Autowired
    YouZanClient youZanClient;

    @Autowired
    TokenParameter tokenParameter;

    /**
     * 每隔5分钟拉取有赞Token
     *
     * @throws SDKException
     */
    @Scheduled(fixedRate = 1000 * 60 * 5)
    public void fixedRateJob() throws SDKException {
        oAuthToken = youZanClient.getOAuthToken(tokenParameter);
    }
}
