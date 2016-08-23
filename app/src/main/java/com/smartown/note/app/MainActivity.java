package com.smartown.note.app;

import com.smartown.library.base.BaseActivity;
import com.smartown.library.common.tool.Tool;
import com.smartown.library.widget.BingView;

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        Tool.jump(MainActivity.this, getString(R.string.app_name), BingFragment.class);
        finish();
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerViews() {

    }

}
