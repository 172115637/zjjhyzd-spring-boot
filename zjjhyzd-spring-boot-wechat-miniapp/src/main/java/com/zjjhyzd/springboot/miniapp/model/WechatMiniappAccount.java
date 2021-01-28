package com.zjjhyzd.springboot.miniapp.model;
import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信小程序用户表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_wechat_miniapp_account")
@Schema(title="WechatMiniappAccount对象", description="微信小程序用户表")
public class WechatMiniappAccount extends BaseModel{

    private static final long serialVersionUID = 1L;

    @Schema(name = "id",description = "主键")
    @JsonProperty(value = "id")
    private String id;

    @Schema(name = "uid",description = "uid")
    @JsonProperty(value = "uid")
    private String uid;

    @Schema(name = "unionid",description = "unionid")
    @JsonProperty(value = "unionid")
    private String unionid;

    @Schema(name = "openid",description = "微信的openid")
    @JsonProperty(value = "openid")
    private String openid;

    @Schema(name = "session_key",description = "微信的session_key")
    @JsonProperty(value = "session_key")
    private String sessionKey;

    @Schema(name = "user_id",description = "绑定的小程序账号id")
    @JsonProperty(value = "user_id")
    private String userId;

}
