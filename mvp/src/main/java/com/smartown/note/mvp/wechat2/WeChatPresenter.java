package com.smartown.note.mvp.wechat2;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.smartown.library.common.presenter.BaseRequestPresenter;
import com.smartown.library.common.presenter.BaseRequestView;
import com.smartown.library.common.tool.RequestTool;
import com.smartown.note.mvp.wechat.WeChatNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:15
 * <p/>
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
    protected String requestData() throws IOException {
        String url = "http://apis.baidu.com/txapi/weixin/wxhot?num=10&rand=1&page=1";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", "4e60f3cc2090dbc9a334dc662b824dba");
        Response response = RequestTool.getInstance().get(url, headers);
        return response.body().string();
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