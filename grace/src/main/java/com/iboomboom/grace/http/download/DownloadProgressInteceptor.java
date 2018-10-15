package com.iboomboom.grace.http.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by hiviiup on 2017/3/28.
 */

public class DownloadProgressInteceptor implements Interceptor {

    DownloadProgressLisenter lisenter;

    public DownloadProgressInteceptor(DownloadProgressLisenter lisenter) {
        this.lisenter = lisenter;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Response response = chain.proceed(chain.request());
        return response.newBuilder().body(new DownloadProgressResponseBody(response.body(), lisenter)).build();
    }
}
