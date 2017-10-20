package com.evayinfo.grace.utils;

import android.Manifest;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by DEVIN on 2017/10/17.
 */

public class ImageUtils {

    public static void loadImage(Object obj, ImageView imageView) {
        Glide.with(AppUtils.context()).load(obj).into(imageView);
    }
}
