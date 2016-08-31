package com.smartown.note.bmob;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-31 10:40
 * <p/>
 * 描述：
 */
public class BmobApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, "793b0b9b25358b72e6f65464382d692c");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }
}
