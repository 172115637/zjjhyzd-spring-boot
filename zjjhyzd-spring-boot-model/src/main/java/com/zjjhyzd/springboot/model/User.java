package com.zjjhyzd.springboot.model;
import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
@Schema(title="User对象", description="用户表")
public class User extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @Schema(name = "username",description = "用户名")
    @JsonProperty(value = "username")
    private String username;

    @Schema(name = "password",description = "密码，加密存储")
    @JsonProperty(value = "password")
    private String password;

    @Schema(name = "phone",description = "注册手机号")
    @JsonProperty(value = "phone")
    private String phone;

    @Schema(name = "email",description = "注册邮箱")
    @JsonProperty(value = "email")
    private String email;

}
