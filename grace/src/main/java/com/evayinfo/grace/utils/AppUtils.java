package com.evayinfo.grace.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.evayinfo.grace.Grace;

/**
 * 常用类
 * Created by DEVIN on 2017/9/29.
 */

public class AppUtils {

    private static Toast toast;

    public static Context context() {
        return Grace.getContext();
    }

    public static void toast(String message) {
        toast(message, Toast.LENGTH_SHORT);
    }

    public static void toast(String message, int duration) {
        if (toast == null)
            toast = Toast.makeText(context(), message, duration);
        toast.setText(message);
        toast.show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = context().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = context().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Nullable
    public static PackageInfo getPackageInfo() {
        PackageManager pm = context().getPackageManager();
        final PackageInfo packageInfo;
        try {
            packageInfo = pm.getPackageInfo(context().getPackageName(), 0);
            return packageInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getVersionCode() {
        if (getPackageInfo() == null)
            return 0;
        return getPackageInfo().versionCode;
    }

    public static String getVersionName() {
        if (getPackageInfo() == null)
            return "";
        return getPackageInfo().versionName;
    }


}
