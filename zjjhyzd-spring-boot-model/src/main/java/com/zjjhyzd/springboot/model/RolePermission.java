package com.zjjhyzd.springboot.model;
import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role_permission")
public class RolePermission extends BaseModel{

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 角色 ID
     */

    private String roleId;

    /**
     * 权限 ID
     */

    private String permissionId;

}
