package com.zjjhyzd.springboot.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public abstract class BaseModel implements Cloneable, Serializable {
    @Schema(name = "version",description = "版本号")
    @Version
    private Integer version;

    @Schema(name = "deleted",description = "是否删除")
    @TableLogic
    private Integer deleted;

    @Schema(name = "create_at",description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty("create_at")
    private LocalDateTime createAt;

    @Schema(name = "create_by",description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty("create_by")
    private String createBy;

    @Schema(name = "update_at",description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonProperty("update_at")
    private LocalDateTime updateAt;

    @Schema(name = "update_by",description = "更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonProperty("update_by")
    private String updateBy;
    // AOP 实现
    @Schema(name = "delete_at",description = "删除时间")
    @JsonProperty("delete_at")
    private LocalDateTime deleteAt;
    // AOP 实现
    @Schema(name = "delete_by",description = "删除者")
    @JsonProperty("delete_by")
    private String deleteBy;
}
