package com.zjjhyzd.springboot.model;

import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(description = "用户表", value = "User")
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @ApiModelProperty(name = "username", value = "用户名")
    @JsonProperty(value = "username")
    private String username;

    @ApiModelProperty(name = "password", value = "密码，加密存储")
    @JsonProperty(value = "password")
    private String password;

    @ApiModelProperty(name = "phone", value = "注册手机号")
    @JsonProperty(value = "phone")
    private String phone;

    @ApiModelProperty(name = "email", value = "注册邮箱")
    @JsonProperty(value = "email")
    private String email;

    @ApiModelProperty(name = "status", value = "用户状态（-1：未启用，0：禁用，1：正常）")
    @JsonProperty(value = "status")
    private Integer status;
}
