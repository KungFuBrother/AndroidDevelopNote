package com.smartown.note.app;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.smartown.library.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews(R.layout.activity_main);
        jump("Hello", HelloFragment.class);
        finish();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void findViews(@LayoutRes int contentView) {
        setContentView(contentView);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerViews() {

    }

}
