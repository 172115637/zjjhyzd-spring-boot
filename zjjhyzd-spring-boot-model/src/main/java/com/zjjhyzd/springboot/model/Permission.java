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
 * 权限表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_permission")
@ApiModel(description="权限表",value = "Permission")
public class Permission extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @ApiModelProperty(name = "parent_id",value = "父权限")
    @JsonProperty(value = "parent_id")
    private String parentId;

    @ApiModelProperty(name = "name",value = "权限名称")
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(name = "enname",value = "权限英文名称")
    @JsonProperty(value = "enname")
    private String enname;

    @ApiModelProperty(name = "url",value = "授权路径")
    @JsonProperty(value = "url")
    private String url;

    @ApiModelProperty(name = "description",value = "备注")
    @JsonProperty(value = "description")
    private String description;

}
