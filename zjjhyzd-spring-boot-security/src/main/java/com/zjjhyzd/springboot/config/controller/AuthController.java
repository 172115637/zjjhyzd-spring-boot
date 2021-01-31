package com.zjjhyzd.springboot.config.controller;

import com.zjjhyzd.springboot.config.service.AuthService;
import com.zjjhyzd.springboot.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<DataModel> login(@RequestBody Map<String, String> params) {
        String token;
        try {
            token = authService.login(params.get("username"), params.get("password"));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(DataModel.instance("用户名或者密码错误", false, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> result = new HashMap<>(2);
        result.put("token", token);
        result.put("authentication", SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>(DataModel.instance(result), HttpStatus.OK);
    }
}
