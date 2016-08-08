package com.smartown.library.base;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:10
 * <p/>
 * 描述：MVP中的Presenter
 */
public class BasePresenter<V extends BaseView> {

    private V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    protected V getView() {
        return view;
    }

}
