package com.evayinfo.demo;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.evayinfo.grace.base.activity.BackActivity;
import com.evayinfo.grace.utils.AppUtils;
import com.evayinfo.grace.utils.FileUtils;
import com.evayinfo.grace.utils.ImageUtils;
import com.evayinfo.grace.zxing.CaptureActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import butterknife.ButterKnife;

public class MainActivity extends BackActivity {

    private static final int SCANNING_REQUEST_CODE = 1001;

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

        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent,SCANNING_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNING_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final Bundle bundle = data.getExtras();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            AppUtils.toast(bundle.getString("result"));
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
