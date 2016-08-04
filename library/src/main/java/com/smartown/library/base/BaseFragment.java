package com.smartown.library.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-28 17:48
 * <p/>
 * 描述：
 */
public abstract class BaseFragment extends Fragment {

    protected LayoutInflater layoutInflater;
    protected View contentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInflater = LayoutInflater.from(getBaseActivity());
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return contentView;
    }

    protected abstract void init();

    protected abstract void findViews();

    protected abstract void initViews();

    protected abstract void registerViews();

    public void findViews(@LayoutRes int contentView) {
        this.contentView = layoutInflater.inflate(contentView, null);
        findViews();
        initViews();
        registerViews();
    }

    public View findViewById(@IdRes int viewId) {
        return contentView.findViewById(viewId);
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public FragmentContainerActivity getContainerActivity() {
        return (FragmentContainerActivity) getActivity();
    }

}
