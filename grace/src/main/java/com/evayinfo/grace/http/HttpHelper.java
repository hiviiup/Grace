package com.evayinfo.grace.http;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.evayinfo.grace.http.download.DownloadProgressInteceptor;
import com.evayinfo.grace.http.download.DownloadProgressLisenter;
import com.evayinfo.grace.utils.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hiviiup on 2017/2/24.
 */

public class HttpHelper {

    private final Retrofit mRetrofit;
    //外网ip
//    public final static String URL = "http://124.133.230.236:8088/dwxf/app/";
    //本地ip
//    public static String BASEURL = "http://111.17.204.158:8088/";
    public final static String BASEURL = "http://172.16.10.144:9083/";
    public static String URL = BASEURL + "sxepp/app/";

    private HttpHelper() {

        OkHttpClient mHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ConnErrorInterceptor())
                .addInterceptor(new DownloadProgressInteceptor(new DownloadProgressLisenter() {
                    @Override
                    public void update(long downloadSize, long totalSize, boolean done) {
                        listener.download(downloadSize, totalSize, done);
                    }
                }))
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                //支持将json转化成model
                .addConverterFactory(GsonConverterFactory.create())
                //支持rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mHttpClient)
                .build();
    }

    private static volatile HttpHelper instance;

    public static HttpHelper getInstance() {
        if (null == instance) {
            synchronized (HttpHelper.class) {
                if (null == instance) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> cls) {
        return mRetrofit.create(cls);
    }

    public void download(String downloadUrl, final File saveFilePath, Subscriber subscriber) {
        create(DownloadApi.class).downFile(downloadUrl).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody requestBody) {
                        return requestBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(new Action1<InputStream>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void call(InputStream inputStream) {
                        try {
                            FileUtils.write(inputStream, saveFilePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public interface OnDownloadListener {
        void download(long downloadSize, long totalSize, boolean done);
    }

    private OnDownloadListener listener;

    public void setOnDownloadListener(OnDownloadListener listener) {
        this.listener = listener;
    }
}
