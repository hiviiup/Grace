package com.iboomboom.grace.http;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.iboomboom.grace.http.download.DownloadProgressInteceptor;
import com.iboomboom.grace.http.download.DownloadProgressLisenter;
import com.iboomboom.grace.utils.AppUtils;
import com.iboomboom.grace.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hiviiup on 2017/2/24.
 */

public class HttpHelper {

    private Retrofit mRetrofit = null;
    private static String URL;

    public static void init(String url) {
        URL = url;
    }

    private HttpHelper(boolean download) {

        if (TextUtils.isEmpty(URL)) {
            AppUtils.toast("请在Application中调用HttpHelper.init(String url)方法设置url!");
            return;
        }

        OkHttpClient mHttpClient = new OkHttpClient.Builder()
                .addInterceptor(download ? new DownloadProgressInteceptor(new DownloadProgressLisenter() {
                    @Override
                    public void update(long downloadSize, long totalSize, boolean done) {
                        listener.download(downloadSize, totalSize, done);
                    }
                }) : new ConnErrorInterceptor())
                .addInterceptor(new SaveCookiesInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
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
    private static volatile HttpHelper downloadInstance;

    public static HttpHelper getInstance() {
        return getInstance(false);
    }

    public static HttpHelper getInstance(boolean download) {
        if (download) {
            if (null == downloadInstance) {
                synchronized (HttpHelper.class) {
                    if (null == downloadInstance) {
                        downloadInstance = new HttpHelper(download);
                    }
                }
            }
            return downloadInstance;
        } else {
            if (null == instance) {
                synchronized (HttpHelper.class) {
                    if (null == instance) {
                        instance = new HttpHelper(download);
                    }
                }
            }
            return instance;
        }

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

    public interface OnCookieLoadListener {
        void onCookieLoad(String cookie);
    }

    private OnCookieLoadListener onCookieLoadListener;

    public void setOnCookieLoadListener(OnCookieLoadListener onCookieLoadListener) {
        this.onCookieLoadListener = onCookieLoadListener;
    }

    public class SaveCookiesInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            //这里获取请求返回的cookie
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                final StringBuffer cookieBuffer = new StringBuffer();
                Observable.from(originalResponse.headers("Set-Cookie"))
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                String[] cookieArray = s.split(";");
                                return cookieArray[0];
                            }
                        })
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String cookie) {
                                cookieBuffer.append(cookie).append(";");
                            }
                        });

                if (onCookieLoadListener != null) {
                    onCookieLoadListener.onCookieLoad(cookieBuffer.toString());
                }
            }
            return originalResponse;
        }
    }
}
