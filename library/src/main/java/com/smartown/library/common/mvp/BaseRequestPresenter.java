package com.smartown.library.common.mvp;

import com.smartown.library.common.tool.RequestTool;

import okhttp3.Request;
import rx.Subscriber;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-08 14:55
 * <p>
 * 描述：
 */
public abstract class BaseRequestPresenter<V extends BaseRequestView> {

    private V view;

    public BaseRequestPresenter(V view) {
        this.view = view;
    }

    public void request() {
        RequestTool.getInstance().get(getRequest(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                requestComplete();
            }

            @Override
            public void onError(Throwable e) {
                requestError(e);
            }

            @Override
            public void onNext(String result) {
                requestResponse(result);
            }
        });
    }

    protected abstract Request getRequest();

    protected abstract void requestResponse(String result);

    protected abstract void requestComplete();

    protected abstract void requestError(Throwable e);

    public V getView() {
        return view;
    }

}
