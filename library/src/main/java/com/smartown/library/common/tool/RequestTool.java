package com.smartown.library.common.tool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-04 16:38
 * <p>
 * 描述：
 */
public class RequestTool {

    private static RequestTool instance;
    private OkHttpClient okHttpClient;

    public static RequestTool getInstance() {
        if (instance == null) {
            instance = new RequestTool();
        }
        return instance;
    }

    public RequestTool() {
        okHttpClient = new OkHttpClient();
    }

    public void get(String url, Subscriber<String> subscriber) {
        get(url, null, subscriber);
    }

    public void get(String url, HashMap<String, String> headers, Subscriber<String> subscriber) {
        get(getRequest(url, headers), subscriber);
    }

    public void get(final Request request, Subscriber<String> subscriber) {
        // 使用IO线程处理, 主线程响应
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                try {
                    subscriber.onNext(get(request));
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(subscriber);
    }

    public String get(Request request) throws IOException {
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 将配置封装为Request对象
     *
     * @param url
     * @param headers
     * @return
     */
    public Request getRequest(String url, HashMap<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if ((headers != null) && (!headers.isEmpty())) {
            Iterator<String> iterator = headers.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                builder.addHeader(key, headers.get(key));
            }
        }
        return builder.build();
    }

}
