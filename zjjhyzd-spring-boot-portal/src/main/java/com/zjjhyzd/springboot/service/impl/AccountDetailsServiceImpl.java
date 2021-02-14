package com.zjjhyzd.springboot.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.exception.WechatMiniappException;
import com.zjjhyzd.springboot.model.*;
import com.zjjhyzd.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    WechatMiniappAccountService wechatMiniappAccountService;

    /**
     * 一键登陆+手机号 自动注册
     *
     * @param session wechat session result
     * @param params  请求参数
     * @param account
     * @return
     * @throws WechatMiniappException
     */
    @Override
    public WechatMiniappUserDetails openidAndMobileLogin(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account) throws WechatMiniappException {
        String phone = params.get("phone");

        User user = userService.getOne(new QueryWrapper<User>().eq("phone", phone));

        // 账户不存在 自动注册
        if (Objects.isNull(account)) {
            if (Objects.nonNull(user)) {
                throw new WechatMiniappException("该手机号码已绑定其它用户");
            }
            user = new User().setPhone(phone).setUsername(phone).setPassword(passwordEncoder.encode(UUID.randomUUID().toString())).setStatus(1);
            userService.save(user);

            // TODO: 2021/2/12 绑定权限
            Role roleUser = roleService.getRoleUser();
            UserRole userRole = new UserRole().setUserId(user.getId()).setRoleId(roleUser.getId());
            userRoleService.save(userRole);

        } else {
            if (Objects.isNull(user))
                throw new WechatMiniappException("无效的手机号");
            if (!account.getUserId().equals(user.getId()))
                throw new WechatMiniappException("该手机号码已绑定其它用户");
        }
        return new WechatMiniappUserDetails(user.getId(), user.getUsername());
    }

    /**
     * 一键登陆 自动注册
     * 登陆后需完善用户信息
     *
     * @param session
     * @param params
     * @param account
     * @return
     */
    @Override
    public WechatMiniappUserDetails openidLogin(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account) {
        // 账户不存在 自动注册
        User user;
        if (Objects.isNull(account)) {

            user = new User().setPassword(passwordEncoder.encode(UUID.randomUUID().toString())).setStatus(-1);
            userService.save(user);

            // TODO: 2021/2/12 绑定权限
            Role roleUser = roleService.getRoleUser();
            UserRole userRole = new UserRole().setUserId(user.getId()).setRoleId(roleUser.getId());
            userRoleService.save(userRole);

        } else {
            user = userService.getById(account.getUserId());
        }
        return new WechatMiniappUserDetails(user.getId(), user.getUsername());
    }

    /**
     * 手机号+密码登陆
     *
     * @param session
     * @param params
     * @param account
     * @return
     * @throws WechatMiniappException
     */
    @Override
    public WechatMiniappUserDetails
    mobileLogin(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account) throws WechatMiniappException {
        String phone = params.get("phone");
        String password = params.get("password");

        User user = userService.getOne(new QueryWrapper<User>().eq("phone", phone));
        // 账户不存在 自动注册
        if (Objects.isNull(user) || !passwordEncoder.matches(password, user.getPassword())) {
            throw new WechatMiniappException("手机号码或者密码错误");
        }
        return new WechatMiniappUserDetails(user.getId(), user.getUsername());
    }
}
