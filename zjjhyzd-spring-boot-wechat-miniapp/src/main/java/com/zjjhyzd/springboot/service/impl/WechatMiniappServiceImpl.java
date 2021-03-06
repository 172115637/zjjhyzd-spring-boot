package com.zjjhyzd.springboot.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.commons.utils.Claims;
import com.zjjhyzd.springboot.commons.utils.JwtTokenUtil;
import com.zjjhyzd.springboot.config.WxMaConfiguration;
import com.zjjhyzd.springboot.exception.WechatMiniappException;
import com.zjjhyzd.springboot.model.WechatMiniappAccount;
import com.zjjhyzd.springboot.model.WechatMiniappUserDetails;
import com.zjjhyzd.springboot.service.AccountDetailsService;
import com.zjjhyzd.springboot.service.WechatMiniappService;
import com.zjjhyzd.springboot.service.WechatMiniappAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class WechatMiniappServiceImpl implements WechatMiniappService {
    private final WechatMiniappAccountService wechatMiniappAccountService;
    private final AccountDetailsService accountDetailsService;

    @Transactional
    @Override
    public String login(Map<String, String> params) throws WxErrorException, WechatMiniappException {
        final WxMaService wxService = WxMaConfiguration.getMaService(params.get("appid"));
        WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(params.get("code"));
        log.info(JSON.toJSONString(session));
        WechatMiniappAccount account = wechatMiniappAccountService.getOne(new QueryWrapper<WechatMiniappAccount>().eq("openid", session.getOpenid()));

        WechatMiniappUserDetails userDetails = accountDetailsService.openidAndMobileLogin(session, params, account);
        if (account == null) {
            account = new WechatMiniappAccount();
            account
                    .setUnionid(session.getUnionid())
                    .setOpenid(session.getOpenid())
                    .setSessionKey(session.getSessionKey())
                    .setUserId(userDetails.getUserId());
            wechatMiniappAccountService.saveOrUpdate(account);
        }

//        Map<String, Object> claims = new HashMap<>();
        Claims claims = new Claims().setSub(userDetails
                .getUsername()).setId(account.getUserId())
                .setOpenId(session.getOpenid()).setSessionKey(session.getSessionKey());

        accountDetailsService.loginAfter(session, params, account);
        return JwtTokenUtil.generateToken(claims);
    }
}
