package com.zjjhyzd.springboot.config.handlers;

import com.alibaba.fastjson.JSON;
import com.zjjhyzd.springboot.commons.utils.JwtTokenUtil;
import com.zjjhyzd.springboot.model.DataModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        Map<Object, Object> result = new HashMap<>();
        result.put("authentication", authentication);
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", authentication.getName());
        claims.put("created", new Date());
        claims.put("session_key", "");
        claims.put("open_id", "");

        result.put("token", JwtTokenUtil.generateToken(claims));
        DataModel model = DataModel.instance().setCode(HttpStatus.OK).setMessage("登陆成功").setSuccess(true).setData(result);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(model));
        writer.close();
    }
}
