package com.smartown.note.mvp.wechat;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.smartown.library.base.BasePresenter;
import com.smartown.library.common.tool.RequestTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:15
 * <p/>
 * 描述：
 */
public class WeChatPresenter implements BasePresenter {

    private WeChatView view;
    private List<WeChatNews> newses;
    private Gson gson;

    public WeChatPresenter(WeChatView view) {
        this.view = view;
        newses = new ArrayList<>();
        gson = new Gson();
    }

    public void request() {// 使用IO线程处理, 主线程响应
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
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String result) {
                if (!TextUtils.isEmpty(result)) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.optInt("code") == 200) {
                            JSONArray jsonArray = jsonObject.optJSONArray("newslist");
                            if (jsonArray != null) {
                                int length = jsonArray.length();
                                for (int i = 0; i < length; i++) {
                                    newses.add(gson.fromJson(jsonArray.optString(i), WeChatNews.class));
                                }
                                view.showData(newses);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public String requestData() throws IOException {
        String url = "http://apis.baidu.com/txapi/weixin/wxhot?num=10&rand=1&page=1";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", "4e60f3cc2090dbc9a334dc662b824dba");
        Response response = RequestTool.getInstance().get(url, headers);
        return response.body().string();
    }

}
