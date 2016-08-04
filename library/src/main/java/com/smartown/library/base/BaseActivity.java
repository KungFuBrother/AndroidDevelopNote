package com.smartown.library.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-28 17:28
 * <p>
 * 描述：
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected abstract void init();

    protected abstract void findViews();

    protected abstract void initViews();

    protected abstract void registerViews();

    protected void findViews(@LayoutRes int contentView) {
        setContentView(contentView);
        findViews();
        initViews();
        registerViews();
    }

}
