package com.zjjhyzd.springboot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@ApiModel(value = "通用响应数据结构类")
@Data
@Accessors(chain = true)
public class DataModel implements Serializable {
    private static final long serialVersionUID = -1291329519310864143L;

    @ApiModelProperty(value = "数据")
    private Object data;

    @ApiModelProperty(value = "请求逻辑结果")
    private Boolean success = true;

    @ApiModelProperty(value = "请求通信结果")
    private Integer code = HttpStatus.OK.value();

    @ApiModelProperty(value = "消息提示")
    private String message;

    private DataModel(Object data) {
        this.data = data;
    }

    /**
     * <p>
     * Description: 默认success为true，code为HttpStatus.OK.value()，其余为null
     * </p>
     *
     * @author Xingmin.cui
     * @date 2017年6月21日
     */
    public static DataModel instance() {
        return new DataModel(null, true, HttpStatus.OK.value(), null);
    }

    public static DataModel instance(Object data) {
        return new DataModel(data, true, HttpStatus.OK.value(), null);
    }

    public static DataModel instance(Object data, Boolean success) {
        return new DataModel(data, success, HttpStatus.OK.value(), null);
    }

    public static DataModel instance(String msg, Boolean success) {
        return new DataModel(null, success, HttpStatus.OK.value(), msg);
    }

    public static DataModel instance(Object data, Boolean success, Integer code) {
        return new DataModel(data, success, code, null);
    }

    public static DataModel instance(Object data, Boolean success, HttpStatus status) {
        return new DataModel(data, success, status.value(), null);
    }

    public static DataModel instance(Object data, Boolean success,
                                     Integer code, String msg) {
        return new DataModel(data, success, code, msg);
    }

    public static DataModel instance(Object data, Boolean success,
                                     HttpStatus status, String msg) {
        return new DataModel(data, success, status.value(), msg);
    }

    public DataModel(Object data, Boolean success, Integer code, String message) {
        super();
        this.data = data;
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
