package com.zjjhyzd.springboot.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private String message;
}
