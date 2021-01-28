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
 * 权限表
 * </p>
 *
 * @author lizhixiang
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_permission")
@Schema(title="Permission对象", description="权限表")
public class Permission extends BaseModel{

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @Schema(name = "parent_id",description = "父权限")
    @JsonProperty(value = "parent_id")
    private String parentId;

    @Schema(name = "name",description = "权限名称")
    @JsonProperty(value = "name")
    private String name;

    @Schema(name = "enname",description = "权限英文名称")
    @JsonProperty(value = "enname")
    private String enname;

    @Schema(name = "url",description = "授权路径")
    @JsonProperty(value = "url")
    private String url;

    @Schema(name = "description",description = "备注")
    @JsonProperty(value = "description")
    private String description;

}
