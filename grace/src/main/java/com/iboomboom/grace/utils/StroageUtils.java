package com.iboomboom.grace.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by DEVIN on 2018/4/28.
 */

public class StroageUtils {

    public static String getStorageImagePath() {
        if (FileUtils.hasSDCard()) {
            return AppUtils.context().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        } else {
            final String path = AppUtils.context().getFilesDir().getAbsolutePath() + File.separator + "IMAGE";
            final File file = new File(path);
            if (!file.exists())
                file.mkdir();
            return path;
        }
    }

    public static String getStorageFilePath() {
        if (FileUtils.hasSDCard()) {
            return AppUtils.context().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        } else {
            final String path = AppUtils.context().getFilesDir().getAbsolutePath() + File.separator + "FILE";
            final File file = new File(path);
            if (!file.exists())
                file.mkdir();
            return path;
        }
    }

    public static String getCachePath() {
        if (FileUtils.hasSDCard()) {
            return AppUtils.context().getExternalCacheDir().getPath();
        } else {
            return AppUtils.context().getCacheDir().getAbsolutePath();
        }
    }

}
