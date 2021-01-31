package com.zjjhyzd.springboot.handlers;

import cn.hutool.core.exceptions.ValidateException;
import com.zjjhyzd.springboot.factory.ResponseEntityFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import com.zjjhyzd.springboot.commons.exception.ServiceException;
import com.zjjhyzd.springboot.model.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

@ControllerAdvice
@Slf4j
@Order(0)
public class GlobalExceptionHandler {
    /**
     * 服务层异常
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<DataModel> serviceException(ServiceException e) {
        log.debug("服务层异常\n" + e.getMessage());
        return ResponseEntityFactory.error(e.getMessage());
    }

    /**
     * 404
     *
     * @return dataModel
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<DataModel> noHandlerFoundException() {
        return ResponseEntityFactory.error("404 Not Found", HttpStatus.NOT_FOUND);
    }

    /**
     * 未知异常捕捉
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataModel> exception(Exception e) {
        e.printStackTrace();
        log.debug("未知异常\n" + e.getMessage());
        return ResponseEntityFactory.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 上传大小过超过最大值
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<DataModel> maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.debug("文件上传超过最大上传大小异常\n" + e.getMessage());
        return ResponseEntityFactory.error("文件最大上传大小为500MB", HttpStatus.BAD_REQUEST);
    }

    /**
     * 缺少请求必传参数
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DataModel> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.debug("缺少请求必传参数异常\n" + e.getMessage());
        return ResponseEntityFactory.error("参数错误", HttpStatus.BAD_REQUEST);
    }

    /**
     * 读取请求失败异常
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataModel> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.debug("读取请求失败异常\n" + e.getMessage());
        return ResponseEntityFactory.error("请携带正确的参数", HttpStatus.BAD_REQUEST);
    }


    /**
     * 请求方式错误异常
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DataModel> methodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.debug("读取请求失败异常\n" + e.getMessage());
        return ResponseEntityFactory.error(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 参数异常
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DataModel> illegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage();
        if (Objects.isNull(message) || StringUtils.isEmpty(message)) {
            message = "参数错误";
        }
        log.debug("参数异常\n" + e.getMessage());
        return ResponseEntityFactory.error(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * 校验异常
     *
     * @param e 异常
     * @return dataModel
     */
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<DataModel> validateException(ValidateException e) {
        log.debug("校验异常\n" + e.getMessage());
        return ResponseEntityFactory.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
