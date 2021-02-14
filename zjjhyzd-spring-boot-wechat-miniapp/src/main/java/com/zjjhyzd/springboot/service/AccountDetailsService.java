package com.zjjhyzd.springboot.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.zjjhyzd.springboot.exception.WechatMiniappException;
import com.zjjhyzd.springboot.model.WechatMiniappAccount;
import com.zjjhyzd.springboot.model.WechatMiniappUserDetails;

import java.util.Map;

/**
 * 小程序账号信息服务类
 */
public interface AccountDetailsService {
    /**
     * 一键登陆+手机号登陆
     *
     * @param session wechat session result
     * @param params  请求参数
     * @return 小程序用户信息 必须返回（如果该用户在系统中不存在请创建）
     */
    WechatMiniappUserDetails openidAndMobileLogin(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account) throws WechatMiniappException;

    /**
     * 一键登陆
     *
     * @return
     */
    default WechatMiniappUserDetails openidLogin(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account) {
        return null;
    }

    /**
     * 手机号登陆
     *
     * @param session
     * @param params
     * @param account
     * @return
     */
    default WechatMiniappUserDetails mobileLogin(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account) throws WechatMiniappException {
        return null;
    }

    default void loginAfter(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account){};
}
