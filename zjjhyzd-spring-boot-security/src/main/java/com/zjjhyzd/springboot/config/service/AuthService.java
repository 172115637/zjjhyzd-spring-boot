package com.zjjhyzd.springboot.config.service;

import org.springframework.security.core.AuthenticationException;

public interface AuthService {
   String login(String username, String password) throws AuthenticationException;
}
