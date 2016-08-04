package com.smartown.library.common.tool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public Response get(String url) throws IOException {
        return get(url, null);
    }

    public Response get(String url, HashMap<String, String> headers) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if ((headers != null) && (!headers.isEmpty())) {
            Iterator<String> iterator = headers.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                builder.addHeader(key, headers.get(key));
            }
        }
        Request request = builder.build();
        return okHttpClient.newCall(request).execute();
    }

}
