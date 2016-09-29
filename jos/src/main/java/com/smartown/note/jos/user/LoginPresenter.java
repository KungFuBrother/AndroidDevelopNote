package com.smartown.note.jos.user;

import com.smartown.library.base.BasePresenter;
import com.smartown.library.common.tool.LogTool;
import com.smartown.library.common.tool.RequestTool;

import rx.Subscriber;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-09-28 14:26
 * <p/>
 * 描述：
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String account, String pwd) {
        String url = "https://oauth.jd.com/oauth/authorize?client_id=C5E61E3DAEC5B3806FF2F982A89C7D52&response_type=code&redirect_uri=urn:ietf:wg:oauth:2.0:oob";
        RequestTool.getInstance().get(url, new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String result) {
                LogTool.i("login", result);
            }
        });
    }

}
