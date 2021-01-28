package com.zjjhyzd.springboot.miniapp.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.commons.utils.JwtTokenUtil;
import com.zjjhyzd.springboot.miniapp.config.WxMaConfiguration;
import com.zjjhyzd.springboot.miniapp.exception.AlreadyBoundException;
import com.zjjhyzd.springboot.miniapp.model.WechatMiniappAccount;
import com.zjjhyzd.springboot.miniapp.model.WechatMiniappUserDetails;
import com.zjjhyzd.springboot.miniapp.service.AccountDetailsService;
import com.zjjhyzd.springboot.miniapp.service.WechatMiniappService;
import com.zjjhyzd.springboot.miniapp.service.WechatMiniappAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class WechatMiniappServiceImpl implements WechatMiniappService {
    private final WechatMiniappAccountService wechatMiniappAccountService;
    private final AccountDetailsService accountDetailsService;

    @Override
    public String login(Map<String, String> params) throws WxErrorException, AlreadyBoundException {
        final WxMaService wxService = WxMaConfiguration.getMaService(params.get("appid"));
        WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(params.get("code"));

        WechatMiniappAccount account = wechatMiniappAccountService.getOne(new QueryWrapper<WechatMiniappAccount>().eq("openid", session.getOpenid()).eq("unionid", session.getUnionid()));

        WechatMiniappUserDetails userDetails = accountDetailsService.invoke(session, params, account);

        account.setUnionid(session.getUnionid()).setOpenid(session.getOpenid()).setSessionKey(session.getSessionKey()).setUserId(userDetails.getUserId());
        wechatMiniappAccountService.saveOrUpdate(account);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        claims.put("session_key", session.getSessionKey());
        claims.put("open_id", session.getOpenid());
        return JwtTokenUtil.generateToken(claims);
    }
}
