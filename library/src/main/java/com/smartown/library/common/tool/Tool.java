package com.smartown.library.common.tool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.UserHandle;

import com.smartown.library.base.FragmentContainerActivity;

import java.util.List;

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

//    public static void startApp(Context context, String packageName) {
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
//        context.startActivity(intent);
//    }

    public static void startApp(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageName);
        List<ResolveInfo> apps = packageManager.queryIntentActivities(resolveIntent, 0);
        ResolveInfo resolveInfo = apps.iterator().next();
        if (resolveInfo != null) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            ComponentName componentName = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            intent.setComponent(componentName);

            UserHandle userHandle = intent.getParcelableExtra("profile");
            if (null == userHandle || userHandle.equals(android.os.Process.myUserHandle())) {
                context.startActivity(intent);
            } else {
                LauncherApps launcherApps = (LauncherApps) context.getSystemService(Context.LAUNCHER_APPS_SERVICE);
                launcherApps.startMainActivity(intent.getComponent(), userHandle, intent.getSourceBounds(), null);
            }
        }
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

    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int convertDpToPx(Context context, int dpSize) {
        return (int) (dpSize * getScreenDensity(context));
    }

}
