package com.smartown.library.common.fragment;

import android.os.Bundle;
import android.webkit.WebView;

import com.smartown.library.base.BaseFragment;
import com.smartown.note.library.R;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-05 16:27
 * <p/>
 * 描述：
 */
public class WebFragment extends BaseFragment {

    public final static String WEB_URL = "WEB_URL";

    private WebView webView;
    private String url = "www.baidu.com";

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(WEB_URL)) {
                url = bundle.getString(WEB_URL);
            }
        }
        findViews(R.layout.common_fragment_web);
    }

    @Override
    protected void findViews() {
        webView = (WebView) findViewById(R.id.common_web);
    }

    @Override
    protected void initViews() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    protected void registerViews() {

    }
}
