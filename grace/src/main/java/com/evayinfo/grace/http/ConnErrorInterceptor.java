package com.evayinfo.grace.http;


import com.evayinfo.grace.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by hiviiup on 2017/2/24.
 * 网络拦截器,当网络断开之后拦截
 */
public class ConnErrorInterceptor implements Interceptor {

    private static final String NET_ERROR_STRING = "网络错误";

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isNetActive()) {
            throw new RuntimeException(NET_ERROR_STRING);
        }
        return chain.proceed(chain.request());
    }


}
