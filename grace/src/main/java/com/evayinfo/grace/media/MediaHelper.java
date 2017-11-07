package com.evayinfo.grace.media;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.evayinfo.grace.utils.AppUtils;
import com.evayinfo.grace.utils.DeviceUtils;

import java.io.File;

/**
 * Created by DEVIN on 2017/11/6.
 * 媒体辅助类
 */

public class MediaHelper {

    public static final int REQUEST_CAMERA = 1001;

    /**
     * 将图片Uri转换成路径
     *
     * @param uri
     * @return
     */
    public static String convertUriToPath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = AppUtils.context().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }

            cursor.close();
        }
        return path;
    }

    /**
     * 调用系统相机进行拍照
     * <p>
     * 如果系统需要兼容23以上，需要在Manifest清单文件中配置以下代码：
     * <p>
     * <provider
     * android:name="android.support.v4.content.FileProvider"
     * android:authorities="devin1024.grace.fileProvider"
     * android:grantUriPermissions="true"
     * android:exported="false">
     * <meta-data
     * android:name="android.support.FILE_PROVIDER_PATHS"
     * android:resource="@xml/file_paths" />
     * </provider>
     *
     * @param activity  调用相机的Activity
     * @param path      图片保存路径
     * @param imageName 图片名称
     */
    public static Uri camera(Activity activity, String path, String imageName) {
        return camera(activity, path, imageName,"devin1024.grace.fileProvider");
    }

    public static Uri camera(Activity activity, String path, String imageName,String authority) {
        if (authority.isEmpty()) {
            authority = "devin1024.grace.fileProvider";
        }
        Uri imageUri;
        File file = new File(path, imageName);
        if (DeviceUtils.getOSVersionSDKINT() > 23) {
            try {
                imageUri = FileProvider.getUriForFile(AppUtils.context(),authority, file);
            } catch (NullPointerException e) {
                throw new RuntimeException("请在Manifest中配置Provider，适配大于23的系统，具体适配参考方法注释中的代码");
            }

        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, REQUEST_CAMERA);
        return imageUri;
    }

}
