package com.evayinfo.grace.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by DEVIN on 2017/11/7.
 */

public class ScreenUtils {

    public static int getWindowWidth() {
        WindowManager wm = (WindowManager) AppUtils.context().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getWindowHeight() {
        WindowManager wm = (WindowManager) AppUtils.context().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = AppUtils.context().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = AppUtils.context().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
