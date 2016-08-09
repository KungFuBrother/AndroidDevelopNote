package com.smartown.note.mvp.wechat2;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.smartown.library.common.mvp.BaseRequestPresenter;
import com.smartown.library.common.mvp.BaseRequestView;
import com.smartown.library.common.tool.RequestTool;
import com.smartown.note.mvp.wechat.WeChatNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-08 14:15
 * <p>
 * 描述：Presenter
 */
public class WeChatPresenter extends BaseRequestPresenter<BaseRequestView<WeChatNews>> {

    private List<WeChatNews> newses;
    private Gson gson;

    public WeChatPresenter(BaseRequestView<WeChatNews> view) {
        super(view);
        newses = new ArrayList<>();
        gson = new Gson();
    }

    @Override
    protected Request getRequest() {
        String url = "http://apis.baidu.com/txapi/weixin/wxhot?num=10&rand=1&page=1";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", "4e60f3cc2090dbc9a334dc662b824dba");
        return RequestTool.getInstance().getRequest(url, headers);
    }

    @Override
    protected void requestResponse(String result) {
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

    @Override
    protected void requestComplete() {

    }

    @Override
    protected void requestError(Throwable e) {

    }

}