package com.smartown.note.mvc.operator;

import com.smartown.library.common.tool.RequestTool;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-04 16:27
 * <p/>
 * 描述：
 */
public class WeChatOperator {

    public void request(Subscriber<? super String> subscriber) {// 使用IO线程处理, 主线程响应
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(requestData());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(subscriber);
    }

    public String requestData() throws IOException {
        String url = "http://apis.baidu.com/txapi/weixin/wxhot?num=10&rand=1&page=1";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", "4e60f3cc2090dbc9a334dc662b824dba");
        Response response = RequestTool.getInstance().get(url, headers);
        return response.body().string();
    }

}
