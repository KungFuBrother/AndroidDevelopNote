package com.smartown.library.common.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.smartown.library.base.BasePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
public abstract class RequestListPresenter extends BasePresenter<RequestListView> {

    private Class aClass;
    private List dataList;
    private Gson gson;

    public RequestListPresenter(RequestListView view, Class aClass) {
        super(view);
        this.aClass = aClass;
        dataList = new ArrayList<>();
        gson = new Gson();
    }

    public void request() {// 使用IO线程处理, 主线程响应
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(requestData());
                subscriber.onCompleted();
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
                                    dataList.add(gson.fromJson(jsonArray.optString(i), aClass));
                                }
                                getView().showData(dataList);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public abstract String requestData();

    public abstract String parseData();

}
