package com.zjjhyzd.springboot.model;
import com.zjjhyzd.springboot.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 《配置表》
 * </p>
 *
 * @author lizhixiang
 * @since 2021-02-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_configure")
@ApiModel(description="《配置表》",value = "Configure")
public class Configure extends BaseModel{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "configure_id",value = " /*主键*/")
    @JsonProperty(value = "configure_id")
    private String configureId;

    @ApiModelProperty(name = "keywords",value = " /*配置key*/")
    @JsonProperty(value = "keywords")
    private String keywords;

    @ApiModelProperty(name = "content",value = " /*配置内容*/")
    @JsonProperty(value = "content")
    private String content;

    @ApiModelProperty(name = "content_sub",value = "/*分裂积分最大值*/")
    @JsonProperty(value = "content_sub")
    private String contentSub;

    @ApiModelProperty(name = "description",value = " /*描述*/")
    @JsonProperty(value = "description")
    private String description;

    @ApiModelProperty(name = "dr",value = " /*逻辑删除标志0:未删除;1:已删除*/")
    @JsonProperty(value = "dr")
    private Integer dr;

    @ApiModelProperty(name = "data_version",value = " /*版本号,从0开始*/")
    @JsonProperty(value = "data_version")
    private Integer dataVersion;

}
