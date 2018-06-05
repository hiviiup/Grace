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

    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof ConnectException) {
            AppUtils.toast("服务器开小差了");
        } else if (e instanceof NullPointerException) {
            AppUtils.toast("数据解析异常");
        } else if (e instanceof SocketTimeoutException) {
            AppUtils.toast("请求超时");
        } else if (e.getMessage().contains("重新登录")) {
//            AccountHelper.deleteToken();
            AppUtils.toast("请重新登录");
        } else if (e.getMessage().contains("未订阅任何网站")) {
        } else  {
            AppUtils.toast(e.getMessage() + "");
        }
        e.printStackTrace();
    }

    @Override
    public abstract void onNext(T t);
}
