package com.evayinfo.demo;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.evayinfo.grace.base.activity.BackActivity;
import com.evayinfo.grace.utils.FileUtils;
import com.evayinfo.grace.utils.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import butterknife.ButterKnife;

public class MainActivity extends BackActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this, this);
    }
}
