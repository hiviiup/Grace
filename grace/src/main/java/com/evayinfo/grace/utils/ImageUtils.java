package com.evayinfo.grace.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by DEVIN on 2017/10/26.
 */

public class ImageUtils {
    public static void loadImage(Object obj, ImageView imageView) {
        Glide.with(AppUtils.context()).load(obj).into(imageView);
    }
}
