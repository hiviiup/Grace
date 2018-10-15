package com.iboomboom.grace.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DEVIN on 2017/10/9.
 */

public class SharedPreUtils {

    private static SharedPreferences sharedPreferences;
    private volatile static SharedPreUtils INSTANCE;

    private SharedPreUtils(String name, int mode) {
        sharedPreferences = AppUtils.context().getSharedPreferences(name, mode);
    }

    public static SharedPreUtils instance(String name) {
        return instance(name, Context.MODE_PRIVATE);
    }

    public static SharedPreUtils instance(String name, int mode) {
        if (INSTANCE == null) {
            synchronized (SharedPreUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SharedPreUtils(name, mode);
                }
            }
        }

        return INSTANCE;
    }

    public SharedPreUtils putString(String key, String value) {
        sharedPreferences.edit().putString(key,value).apply();
        return INSTANCE;
    }

    public SharedPreUtils putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key,value).apply();
        return INSTANCE;
    }

    public SharedPreUtils putInt(String key, int value) {
        sharedPreferences.edit().putInt(key,value).apply();
        return INSTANCE;
    }

    public SharedPreUtils putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key,value).apply();
        return INSTANCE;
    }

    public SharedPreUtils putLong(String key, long value) {
        sharedPreferences.edit().putLong(key,value).apply();
        return INSTANCE;
    }

    public String getString(String key,String defaultValue) {
        return sharedPreferences.getString(key,defaultValue);
    }

    public boolean getBoolean(String key,boolean defaultValue) {
        return sharedPreferences.getBoolean(key,defaultValue);
    }

    public int getInt(String key,int defaultValue) {
        return sharedPreferences.getInt(key,defaultValue);
    }

    public float getFloat(String key,float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public float getLong(String key,long defaultValue) {
        return sharedPreferences.getLong(key,defaultValue);
    }
}
