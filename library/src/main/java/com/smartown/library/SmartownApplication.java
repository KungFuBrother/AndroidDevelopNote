package com.smartown.library;

import android.app.Application;

import com.smartown.library.common.tool.ToastTool;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-04 13:09
 * <p/>
 * 描述：
 */
public class SmartownApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastTool.init(this);
    }
}
