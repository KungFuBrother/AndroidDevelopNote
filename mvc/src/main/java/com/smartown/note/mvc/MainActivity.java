package com.smartown.note.mvc;

import com.smartown.library.base.BaseActivity;
import com.smartown.library.common.tool.Tool;
import com.smartown.note.mvc.controller.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        Tool.jump(MainActivity.this, getString(R.string.app_name), MainFragment.class);
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
