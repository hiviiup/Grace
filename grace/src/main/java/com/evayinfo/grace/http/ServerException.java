package com.evayinfo.grace.http;

/**
 * Created by DEVIN on 2018/7/3.
 */

public class ServerException extends RuntimeException {

    private int code;

    public ServerException(String message,int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
