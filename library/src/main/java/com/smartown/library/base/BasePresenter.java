package com.smartown.library.base;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:10
 * <p/>
 * 描述：MVP中的Presenter
 */
public class BasePresenter<T extends BaseView> {

    private T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    protected T getView() {
        return view;
    }

}
