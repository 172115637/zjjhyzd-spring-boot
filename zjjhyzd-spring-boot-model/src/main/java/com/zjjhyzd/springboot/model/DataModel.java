package com.zjjhyzd.springboot.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class DataModel implements Serializable {
    private static final long serialVersionUID = -1291329519310864143L;

    private Object data;
    private Boolean success;
    private Integer code = HttpStatus.OK.value();
    private String message;

    @SuppressWarnings("unused")
    private DataModel() {
        super();
    }

    /**
     *
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

    public Object getData() {
        return data;
    }

    public DataModel setData(Object data) {
        this.data = data;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public DataModel setSuccess(Boolean success) {
        this.success = success;
        return this;
    }



    public Integer getCode() {
        return code;
    }

    public DataModel setCode(Integer code) {
        this.code = code;
        return this;
    }

    public DataModel setCode(HttpStatus status) {
        this.code = status.value();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DataModel setMessage(String msg) {
        this.message = msg;
        return this;
    }

    @Override
    public String toString() {
        return "[data=" + data + ", success=" + success + ", code=" + code
                + ", msg=" + message + "]";
    }
}
