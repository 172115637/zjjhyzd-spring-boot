package com.zjjhyzd.springboot.config.controller;

import com.zjjhyzd.springboot.config.service.AuthService;
import com.zjjhyzd.springboot.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<DataModel> login(@RequestBody Map<String, String> params) {
        String token;
        try {
            token = authService.login(params.get("username"), params.get("password"));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(DataModel.instance("用户名或者密码错误", false).setCode(HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }

        Map<String, Object> result = new HashMap<>(1);
        result.put("token", token);
        return new ResponseEntity<>(DataModel.instance(result), HttpStatus.OK);
    }

    @RequestMapping("/userinfo")
    public ResponseEntity<DataModel> userinfo() {
        Map<String, Object> result = new HashMap<>(1);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles =new ArrayList<>();
        authorities.forEach(item->roles.add(item.getAuthority()));
        result.put("roles", roles);
        result.put("name",authentication.getName());
        return new ResponseEntity<>(DataModel.instance(result), HttpStatus.OK);
    }
}
