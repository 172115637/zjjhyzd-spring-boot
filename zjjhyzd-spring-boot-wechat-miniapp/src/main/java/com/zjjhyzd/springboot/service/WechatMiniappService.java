package com.zjjhyzd.springboot.service;

import com.zjjhyzd.springboot.exception.WechatMiniappException;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.Map;

public interface WechatMiniappService {
    /**
     * 小程序登陆
     *
     * @param params 请求参数 必须携带code appid
     * @return JWT Token
     * @throws WxErrorException appid与本系统appid不一致
     */
    String login(Map<String, String> params) throws WxErrorException, WechatMiniappException;
}
