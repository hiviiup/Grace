package com.evayinfo.grace.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.evayinfo.grace.Grace;

import es.dmoral.toasty.Toasty;

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
        if (toast == null) {
            toast = Toasty.info(context(), message, duration);
        }
        toast.setText(message);
        toast.show();
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
        if (getPackageInfo() == null) {
            return 0;
        }
        return getPackageInfo().versionCode;
    }

    public static String getVersionName() {
        if (getPackageInfo() == null) {
            return "";
        }
        return getPackageInfo().versionName;
    }


}
