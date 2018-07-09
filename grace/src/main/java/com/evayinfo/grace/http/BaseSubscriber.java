package com.evayinfo.grace.http;

import com.evayinfo.grace.utils.AppUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by hiviiup on 2017/2/27.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        hideProgress();
        if (e instanceof ServerException) {
            //服务端加载数据时发生的错误信息
            onResponseError(((ServerException) e).getCode());
            AppUtils.toast(e.getMessage());
        } else {
            if (e instanceof ConnectException) {
                AppUtils.toast("服务器开小差了");
            } else if (e instanceof NullPointerException) {
                AppUtils.toast("数据解析异常");
            } else if (e instanceof SocketTimeoutException || e.getMessage().contains("failed to connect to")) {
                AppUtils.toast("请求超时");
            } else {
                AppUtils.toast("服务器错误");
            }
        }
        e.printStackTrace();
    }

    private void hideProgress() {

    }

    public void onResponseError(String code) {

    }

    @Override
    public abstract void onNext(T t);
}
