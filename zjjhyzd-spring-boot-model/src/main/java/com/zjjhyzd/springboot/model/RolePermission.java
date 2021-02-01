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
 * 角色权限表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role_permission")
@ApiModel(description="角色权限表",value = "RolePermission")
public class RolePermission extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @ApiModelProperty(name = "role_id",value = "角色 ID")
    @JsonProperty(value = "role_id")
    private String roleId;

    @ApiModelProperty(name = "permission_id",value = "权限 ID")
    @JsonProperty(value = "permission_id")
    private String permissionId;

}
