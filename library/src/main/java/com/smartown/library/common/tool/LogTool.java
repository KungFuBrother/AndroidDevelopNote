package com.smartown.library.common.tool;

import android.util.Log;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-31 10:49
 * <p/>
 * 描述：
 */
public class LogTool {

    public static boolean enable = true;

    public static void i(String tag, String msg) {
        if (enable) {
            Log.i(tag, msg);
        }
    }

}
