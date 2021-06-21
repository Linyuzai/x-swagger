package com.github.linyuzai.xswagger.exception;

public class XSwaggerException extends RuntimeException {

    public XSwaggerException(String message) {
        super(message);
    }

    public XSwaggerException(String message, Throwable cause) {
        super(message, cause);
    }

    public XSwaggerException(Throwable cause) {
        super(cause);
    }
}
