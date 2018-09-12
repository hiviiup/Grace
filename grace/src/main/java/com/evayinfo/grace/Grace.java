package com.evayinfo.grace;

import android.annotation.SuppressLint;
import android.content.Context;

import com.evayinfo.grace.log.CrashHandler;
import com.evayinfo.grace.log.MyCsvFormatStrategy;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by DEVIN on 2017/9/28.
 */

public class Grace {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;

        Logger.addLogAdapter(new AndroidLogAdapter());
        MyCsvFormatStrategy formatStrategy = MyCsvFormatStrategy.newBuilder().build();
        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));

        CrashHandler.getInstance().init(context);
    }

    public static Context getContext() {
        if (mContext == null) {
            throw new RuntimeException("you should init grace in application!");
        }
        return mContext;
    }
}
