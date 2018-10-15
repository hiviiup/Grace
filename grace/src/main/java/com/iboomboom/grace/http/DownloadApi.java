package com.iboomboom.grace.http;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by DEVIN on 2018/6/5.
 */

public interface DownloadApi {

    /**
     * 下载文件
     *
     * @param downloadUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Url String downloadUrl);
}
