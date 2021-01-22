package com.zjjhyzd.springboot.model;
import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
public class User extends BaseModel{

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码，加密存储
     */

    private String password;

    /**
     * 注册手机号
     */

    private String phone;

    /**
     * 注册邮箱
     */

    private String email;

}
