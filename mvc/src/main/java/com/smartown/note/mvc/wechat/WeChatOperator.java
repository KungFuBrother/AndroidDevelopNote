package com.smartown.note.mvc.wechat;

import com.smartown.library.common.tool.RequestTool;

import java.util.HashMap;

import rx.Subscriber;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-04 16:27
 * <p>
 * 描述：Model
 */
public class WeChatOperator {

    public void request(Subscriber<String> subscriber) {
        String url = "http://apis.baidu.com/txapi/weixin/wxhot?num=10&rand=1&page=1";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("apikey", "4e60f3cc2090dbc9a334dc662b824dba");
        RequestTool.getInstance().get(url, headers, subscriber);
    }

}
