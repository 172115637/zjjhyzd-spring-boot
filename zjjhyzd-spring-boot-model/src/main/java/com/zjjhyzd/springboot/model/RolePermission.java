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
 * 角色权限表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role_permission")
@Schema(title="RolePermission对象", description="角色权限表")
public class RolePermission extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @Schema(name = "role_id",description = "角色 ID")
    @JsonProperty(value = "role_id")
    private String roleId;

    @Schema(name = "permission_id",description = "权限 ID")
    @JsonProperty(value = "permission_id")
    private String permissionId;

}
