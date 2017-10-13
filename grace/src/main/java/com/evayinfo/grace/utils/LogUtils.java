package com.evayinfo.grace.utils;

import android.util.Log;

import com.evayinfo.grace.BuildConfig;

import java.security.PublicKey;

/**
 * Created by DEVIN on 2017/9/30.
 */

public class LogUtils {

    private static boolean DEBUG = BuildConfig.DEBUG ;

    public static void e(String msg) {
        if (DEBUG)
            Log.e("LogUtils", msg);
    }

    public static void d(String msg) {
        if (DEBUG)
            Log.d("LogUtils", msg);
    }

    public static void i(String msg) {
        if (DEBUG)
            Log.i("LogUtils", msg);
    }

    public static void w(String msg) {
        if (DEBUG)
            Log.w("LogUtils", msg);
    }
}
