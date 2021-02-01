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
 * 用户角色表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user_role")
@ApiModel(description="用户角色表",value = "UserRole")
public class UserRole extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @ApiModelProperty(name = "user_id",value = "用户 ID")
    @JsonProperty(value = "user_id")
    private String userId;

    @ApiModelProperty(name = "role_id",value = "角色 ID")
    @JsonProperty(value = "role_id")
    private String roleId;

}
