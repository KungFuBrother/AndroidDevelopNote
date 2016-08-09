package com.smartown.note.mvp.wechat;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.smartown.library.base.BasePresenter;
import com.smartown.library.common.tool.RequestTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Subscriber;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-08 14:15
 * <p>
 * 描述：Presenter
 */
public class WeChatPresenter extends BasePresenter<WeChatView> {

    private List<WeChatNews> newses;
    private Gson gson;

    public WeChatPresenter(WeChatView view) {
        super(view);
        newses = new ArrayList<>();
        gson = new Gson();
    }

    public void request() {
        String url = "http://apis.baidu.com/txapi/weixin/wxhot?num=10&rand=1&page=1";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", "4e60f3cc2090dbc9a334dc662b824dba");
        RequestTool.getInstance().get(url, headers, new Subscriber<String>() {
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
                                getView().showData(newses);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
