package com.zjjhyzd.springboot.model;
import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 权限表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_permission")
public class Permission extends BaseModel{

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 父权限
     */

    private String parentId;

    /**
     * 权限名称
     */

    private String name;

    /**
     * 权限英文名称
     */

    private String enname;

    /**
     * 授权路径
     */

    private String url;

    /**
     * 备注
     */

    private String description;

}
