package com.evayinfo.grace.http;

import com.evayinfo.grace.utils.AppUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by hiviiup on 2017/2/27.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    public static final int CONN_FAIL = -1;
    public static final int NULL_POINTER = -2;
    public static final int TIME_OUT = -3;
    public static final int UNKNOWN = -4;

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        onFinish();
        if (e instanceof ServerException) {
            //服务端加载数据时发生的错误信息
            onResponseError(((ServerException) e).getCode(), e);
        } else {
            if (e instanceof ConnectException) {
                onResponseError(CONN_FAIL, e);
            } else if (e instanceof NullPointerException) {
                onResponseError(NULL_POINTER, e);
            } else if (e instanceof SocketTimeoutException || e.getMessage().contains("failed to connect to")) {
                onResponseError(TIME_OUT, e);
            } else {
                onResponseError(UNKNOWN, e);
            }
        }
        e.printStackTrace();
    }

    public void onFinish() {
    }

    public void onResponseError(int code, Throwable e) {

    }

    @Override
    public abstract void onNext(T t);
}
