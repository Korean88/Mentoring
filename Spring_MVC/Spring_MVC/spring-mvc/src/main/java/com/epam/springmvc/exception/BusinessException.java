package com.epam.springmvc.exception;

/**
 * Created by Andrey Yun on 09.05.2016.
 */
public class BusinessException extends RuntimeException {

    private String message;

    public BusinessException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
