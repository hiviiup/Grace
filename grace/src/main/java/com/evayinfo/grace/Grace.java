package com.evayinfo.grace;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by DEVIN on 2017/9/28.
 */

public class Grace {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        if (mContext ==null)
            throw new RuntimeException("you should init grace in application!");
        return mContext;
    }
}
