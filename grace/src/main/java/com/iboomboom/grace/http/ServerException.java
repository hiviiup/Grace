package com.iboomboom.grace.http;

/**
 * Created by DEVIN on 2018/7/3.
 */

public class ServerException extends RuntimeException {

    private String code;

    public ServerException(String message,String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
