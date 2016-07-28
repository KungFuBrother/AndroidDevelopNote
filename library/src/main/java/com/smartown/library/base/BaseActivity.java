package com.smartown.library.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-28 17:28
 * <p/>
 * 描述：
 */
public abstract class BaseActivity extends FragmentActivity {

    protected abstract void init();

    protected abstract void findViews(@LayoutRes int contentLayout);

    protected abstract void initViews();

    protected abstract void registerViews();

    public void jump(String title, Class fragmentClass, Bundle fragmentArgument) {
        Intent intent = new Intent(BaseActivity.this, FragmentContainerActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("fragmentClass", fragmentClass.getName());
        if (fragmentArgument != null) {
            intent.putExtra("fragmentArgument", fragmentArgument);
        }
        startActivity(intent);
    }

    public void jump(String title, Class fragmentClass) {
        jump(title, fragmentClass, null);
    }

}
