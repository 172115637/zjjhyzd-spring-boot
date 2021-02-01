package com.zjjhyzd.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AlreadyBoundException extends Exception{
    private final String message;
}
