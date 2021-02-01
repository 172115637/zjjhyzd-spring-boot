package com.zjjhyzd.springboot.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.springboot.commons.utils.Claims;
import com.zjjhyzd.springboot.commons.utils.JwtTokenUtil;
import com.zjjhyzd.springboot.config.service.AuthService;
import com.zjjhyzd.springboot.config.service.UserDetailsServiceImpl;
import com.zjjhyzd.springboot.service.UserService;
import com.zjjhyzd.springboot.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;

    @Override
    public String login(String username, String password) throws AuthenticationException {
        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        User user = userService.getOne(new QueryWrapper<User>().eq("username", username).select("username"));

        Claims claims = new Claims().setSub(userDetails
                .getUsername()).setId(user.getId());
        return JwtTokenUtil.generateToken(claims);
    }
}
