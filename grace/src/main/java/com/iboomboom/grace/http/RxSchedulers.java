package com.iboomboom.grace.http;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hiviiup on 2017/2/24.
 * 异步处理
 */

public class RxSchedulers {

    public static <T> Observable.Transformer<HttpResult<T>, HttpResult<T>> switchSchedulers() {
        return new Observable.Transformer<HttpResult<T>, HttpResult<T>>() {
            @Override
            public Observable<HttpResult<T>> call(Observable<HttpResult<T>> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
