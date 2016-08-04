package com.smartown.library.common.tool;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-04 11:21
 * <p/>
 * 描述：
 */
public class ToastTool {

    public static Toast toast;

    public static void init(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    public static void show(String s) {
        if (toast == null) {
            return;
        }
        toast.setText(s);
        toast.show();
    }

}
