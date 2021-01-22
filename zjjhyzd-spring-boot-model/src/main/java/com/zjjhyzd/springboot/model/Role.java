package com.zjjhyzd.springboot.model;
import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role")
public class Role extends BaseModel{

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 父角色
     */

    private String parentId;

    /**
     * 角色名称
     */

    private String name;

    /**
     * 角色英文名称
     */

    private String enname;

    /**
     * 备注
     */

    private String description;

}
