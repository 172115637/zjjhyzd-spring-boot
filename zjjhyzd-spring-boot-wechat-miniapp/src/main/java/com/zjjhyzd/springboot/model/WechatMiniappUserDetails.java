package com.zjjhyzd.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WechatMiniappUserDetails {
    private final String userId;
    private final String username;
}
