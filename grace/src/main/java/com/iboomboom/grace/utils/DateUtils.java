package com.iboomboom.grace.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DEVIN on 2017/9/30.
 */

public class DateUtils {

    public static String mills2time(long mills) {
        final String timePattern = "yyyy-MM-dd hh:mm";
        return mills2time(mills, timePattern);
    }

    @SuppressLint("SimpleDateFormat")
    public static String mills2time(long mills, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(mills));
    }
}
