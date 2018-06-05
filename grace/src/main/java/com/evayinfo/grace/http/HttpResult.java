package com.evayinfo.grace.http;

/**
 * Created by hiviiup on 2017/3/8.
 */

public class HttpResult<T> extends Object {
    boolean flag;
    String msg;
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
