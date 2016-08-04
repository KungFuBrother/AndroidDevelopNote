package com.smartown.library.common.tool;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.smartown.library.base.FragmentContainerActivity;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-04 10:44
 * <p/>
 * 描述：
 */
public class Tool {

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void startApp(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    public static void jump(Context context, String title, Class fragmentClass, Bundle fragmentArgument) {
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("fragmentClass", fragmentClass.getName());
        if (fragmentArgument != null) {
            intent.putExtra("fragmentArgument", fragmentArgument);
        }
        context.startActivity(intent);
    }

    public static void jump(Context context, String title, Class fragmentClass) {
        jump(context, title, fragmentClass, null);
    }

}
