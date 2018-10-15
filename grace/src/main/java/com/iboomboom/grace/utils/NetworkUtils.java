package com.iboomboom.grace.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by DEVIN on 2017/10/16.
 */

public class NetworkUtils {

    public static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) AppUtils.context().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isNetActive() {
        NetworkInfo net = getConnectivityManager().getActiveNetworkInfo();
        return net != null && net.isConnectedOrConnecting();
    }

    public static boolean isWifi() {
        final ConnectivityManager cm = getConnectivityManager();
        final NetworkInfo net = cm.getActiveNetworkInfo();
        return net != null && net.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
