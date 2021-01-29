package com.zjjhyzd.springboot.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.miniapp.exception.AlreadyBoundException;
import com.zjjhyzd.springboot.miniapp.model.WechatMiniappAccount;
import com.zjjhyzd.springboot.miniapp.model.WechatMiniappUserDetails;
import com.zjjhyzd.springboot.miniapp.service.AccountDetailsService;
import com.zjjhyzd.springboot.miniapp.service.UserService;
import com.zjjhyzd.springboot.model.User;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @GlobalTransactional
    public WechatMiniappUserDetails invoke(WxMaJscode2SessionResult session, Map<String, String> params, WechatMiniappAccount account) throws AlreadyBoundException {
        String phone = params.get("phone");

        User user = userService.getOne(new QueryWrapper<User>().eq("phone", phone));
        // 账户不存在
        if (null == account) {
            user = new User().setPhone(phone).setUsername(phone).setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            userService.save(user);
            throw new AlreadyBoundException("该手机号码已绑定用户");
            // 绑定权限
        } else {
            if (user == null || !account.getUserId().equals(user.getId()))
                throw new AlreadyBoundException("该手机号码已绑定用户");
        }
        return new WechatMiniappUserDetails(user.getId(), user.getUsername());
    }
}
