package com.smartown.library.common.tool;

import android.support.annotation.NonNull;

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

    public String get(String url) throws IOException {
        return get(url, null);
    }

    public String get(String url, HashMap<String, String> headers) throws IOException {
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
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public static class RequestBuilder {

        public final static String METHOD_POST = "POST";
        public final static String METHOD_GET = "GET";

        private String method = METHOD_POST;
        private String url = "";
        private HashMap<String, String> parameters;
        private HashMap<String, String> headers;

        public RequestBuilder(String method) {
            this.method = method;
            parameters = new HashMap<>();
            headers = new HashMap<>();
        }

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder addParameters(String key, String value) {
            parameters.put(key, value);
            return this;
        }

        public RequestBuilder addHeaders(@NonNull String key, @NonNull String value) {
            headers.put(key, value);
            return this;
        }

//        public Request build(){
//            Request.Builder builder = new Request.Builder();
//            builder.url(url);
//        }

    }

}
