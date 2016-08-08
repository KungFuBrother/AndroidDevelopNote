package com.smartown.note.mvp;

import com.smartown.library.base.BaseActivity;
import com.smartown.library.common.tool.Tool;
import com.smartown.note.mvp.wechat.WeChatFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        Tool.jump(MainActivity.this, getString(R.string.app_name), WeChatFragment.class);
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
