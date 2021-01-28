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
 * 用户角色表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user_role")
@Schema(title="UserRole对象", description="用户角色表")
public class UserRole extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @Schema(name = "user_id",description = "用户 ID")
    @JsonProperty(value = "user_id")
    private String userId;

    @Schema(name = "role_id",description = "角色 ID")
    @JsonProperty(value = "role_id")
    private String roleId;

}
