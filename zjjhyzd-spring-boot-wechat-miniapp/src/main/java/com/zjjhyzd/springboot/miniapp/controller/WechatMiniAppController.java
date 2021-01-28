package com.zjjhyzd.springboot.miniapp.controller;

import com.zjjhyzd.springboot.miniapp.exception.AlreadyBoundException;
import com.zjjhyzd.springboot.model.DataModel;
import com.zjjhyzd.springboot.miniapp.service.WechatMiniappService;
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

@RestController("/wechat-miniapp")
@RequestMapping
public class WechatMiniAppController {
    @Autowired
    private WechatMiniappService wechatMiniappService;

    @PostMapping("/login")
    public ResponseEntity<DataModel> login(@RequestBody Map<String, String> params) {
        try {
            String token = wechatMiniappService.login(params);
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            return new ResponseEntity<>(DataModel.instance(result, true, HttpStatus.OK), HttpStatus.OK);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return new ResponseEntity<>(DataModel.instance("系统异常", false), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (AlreadyBoundException e) {
            return new ResponseEntity<>(DataModel.instance(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}
