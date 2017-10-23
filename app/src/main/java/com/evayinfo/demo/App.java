package com.evayinfo.demo;

import android.app.Application;

import com.evayinfo.grace.Grace;

/**
 * Created by DEVIN on 2017/10/20.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Grace.init(this);
    }
}
