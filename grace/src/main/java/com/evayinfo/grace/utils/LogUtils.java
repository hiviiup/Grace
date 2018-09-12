package com.evayinfo.grace.utils;

import android.util.Log;

import com.evayinfo.grace.BuildConfig;
import com.orhanobut.logger.Logger;

import java.security.PublicKey;

/**
 * Created by DEVIN on 2017/9/30.
 */

public class LogUtils {

    private static boolean DEBUG = BuildConfig.DEBUG;
    private static String TAG = "LogUtils";

    public static void debug(boolean debug) {
        DEBUG = debug;
    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }

    public static void w(String msg) {
        Logger.w(msg);
    }
}
