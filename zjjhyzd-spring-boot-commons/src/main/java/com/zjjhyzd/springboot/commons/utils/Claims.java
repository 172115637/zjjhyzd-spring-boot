package com.zjjhyzd.springboot.commons.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Claims {
    private String sub;
    private String id;
    private Date created = new Date();
    private String sessionKey;
    private String openId;
}
