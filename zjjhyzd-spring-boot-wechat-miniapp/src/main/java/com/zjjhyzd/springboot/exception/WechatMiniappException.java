package com.zjjhyzd.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WechatMiniappException extends Exception{
    private final String message;
}
