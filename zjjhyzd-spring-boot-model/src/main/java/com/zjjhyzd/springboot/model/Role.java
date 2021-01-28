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
 * 角色表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role")
@Schema(title="Role对象", description="角色表")
public class Role extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @Schema(name = "parent_id",description = "父角色")
    @JsonProperty(value = "parent_id")
    private String parentId;

    @Schema(name = "name",description = "角色名称")
    @JsonProperty(value = "name")
    private String name;

    @Schema(name = "enname",description = "角色英文名称")
    @JsonProperty(value = "enname")
    private String enname;

    @Schema(name = "description",description = "备注")
    @JsonProperty(value = "description")
    private String description;

}
