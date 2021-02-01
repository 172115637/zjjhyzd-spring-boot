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
 * 角色表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role")
@ApiModel(description="角色表",value = "Role")
public class Role extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @ApiModelProperty(name = "parent_id",value = "父角色")
    @JsonProperty(value = "parent_id")
    private String parentId;

    @ApiModelProperty(name = "name",value = "角色名称")
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(name = "enname",value = "角色英文名称")
    @JsonProperty(value = "enname")
    private String enname;

    @ApiModelProperty(name = "description",value = "备注")
    @JsonProperty(value = "description")
    private String description;

}
