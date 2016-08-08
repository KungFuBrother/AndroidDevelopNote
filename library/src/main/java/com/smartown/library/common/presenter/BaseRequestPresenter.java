package com.smartown.library.common.presenter;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:55
 * <p/>
 * 描述：
 */
public abstract class BaseRequestPresenter<V extends BaseRequestView> {

    private V view;

    public BaseRequestPresenter(V view) {
        this.view = view;
    }

    public void request() {// 使用IO线程处理, 主线程响应
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(requestData());
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                requestComplete();
            }

            @Override
            public void onError(Throwable e) {
                requestError(e);
            }

            @Override
            public void onNext(String result) {
                requestResponse(result);
            }
        });
    }

    protected abstract String requestData() throws IOException;

    protected abstract void requestResponse(String result);

    protected abstract void requestComplete();

    protected abstract void requestError(Throwable e);

    public V getView() {
        return view;
    }

}
