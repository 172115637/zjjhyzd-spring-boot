package com.zjjhyzd.springboot.controller;

import com.zjjhyzd.springboot.exception.AlreadyBoundException;
import com.zjjhyzd.springboot.model.DataModel;
import com.zjjhyzd.springboot.service.WechatMiniappService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat-miniapp")
public class WechatMiniAppController {
    @Autowired
    private WechatMiniappService wechatMiniappService;

    /**
     * 微信小程序登陆
     *
     * @param params 需携带code appid这两个必传参数，其余参数详见 AccountDetailsServiceImpl
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<DataModel> login(@RequestBody Map<String, String> params) {
        try {
            String token = wechatMiniappService.login(params);
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            return new ResponseEntity<>(DataModel.instance(result, true, HttpStatus.OK), HttpStatus.OK);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return new ResponseEntity<>(DataModel.instance("系统异常", false, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        } catch (AlreadyBoundException e) {
            return new ResponseEntity<>(DataModel.instance(e.getMessage(), false, HttpStatus.BAD_REQUEST), HttpStatus.OK);
        }
    }
}
