package com.iboomboom.grace.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;


/**
 * Created by DEVIN on 2017/10/26.
 */

public class ImageUtils {
    public static void loadImage(Object obj, ImageView imageView) {
        Glide.with(AppUtils.context()).load(obj).into(imageView);
    }

    public static String image2Base64(File file) {
        if (!file.exists()) {
            AppUtils.toast("文件不存在,无法打开");
            return "";
        }
        final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        return Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
    }

}
