package com.zjjhyzd.springboot.factory;

import com.zjjhyzd.springboot.model.DataModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityFactory {
    private ResponseEntityFactory() {
    }

    public static ResponseEntity<DataModel> success() {
        return new ResponseEntity<>(DataModel.instance(null, true), HttpStatus.OK);
    }

    public static ResponseEntity<DataModel> success(Object data) {
        return new ResponseEntity<>(DataModel.instance(data, true, HttpStatus.OK), HttpStatus.OK);
    }

    public static ResponseEntity<DataModel> success(String message) {
        return new ResponseEntity<>(DataModel.instance(null, true, HttpStatus.OK, message), HttpStatus.OK);
    }

    public static ResponseEntity<DataModel> success(Object data, String message) {
        return new ResponseEntity<>(DataModel.instance(data, true, HttpStatus.OK, message), HttpStatus.OK);
    }

    public static ResponseEntity<DataModel> error() {
        return new ResponseEntity<>(DataModel.instance(null, false, HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

    public static ResponseEntity<DataModel> error(String message) {
        return new ResponseEntity<>(DataModel.instance(null, false, HttpStatus.BAD_REQUEST, message), HttpStatus.OK);
    }

    public static ResponseEntity<DataModel> error(String message, Integer code) {
        return new ResponseEntity<>(DataModel.instance(null, false, code, message), HttpStatus.OK);
    }

    public static ResponseEntity<DataModel> error(String message, HttpStatus status) {
        return new ResponseEntity<>(DataModel.instance(null, false, status, message), HttpStatus.OK);
    }
}
