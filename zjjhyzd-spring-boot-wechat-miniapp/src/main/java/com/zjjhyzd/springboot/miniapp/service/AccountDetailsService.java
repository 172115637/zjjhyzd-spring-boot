package com.zjjhyzd.springboot.miniapp.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.zjjhyzd.springboot.miniapp.exception.AlreadyBoundException;
import com.zjjhyzd.springboot.miniapp.model.WechatMiniappAccount;
import com.zjjhyzd.springboot.miniapp.model.WechatMiniappUserDetails;

import java.util.Map;

/**
 * 小程序账号信息服务类
 */
public interface AccountDetailsService {
    /**
     * @param session wechat session result
     * @param params  请求参数
     * @return 小程序用户信息 必须返回（如果该用户在系统中不存在请创建）
     */
    WechatMiniappUserDetails invoke(WxMaJscode2SessionResult session, Map<String, String> params , WechatMiniappAccount account) throws AlreadyBoundException;
}
