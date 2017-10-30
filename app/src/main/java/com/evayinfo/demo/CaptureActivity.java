package com.evayinfo.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.evayinfo.grace.zxing.ViewfinderView;
import com.google.zxing.Result;

/**
 * Created by DEVIN on 2017/10/27.
 */

public class CaptureActivity extends com.evayinfo.grace.zxing.CaptureActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_capture;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public SurfaceView getSurfaceView() {
        return findViewById(R.id.preview_view);
    }

    @Override
    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        super.dealDecode(rawResult, barcode, scaleFactor);
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_LONG).show();
//        对此次扫描结果不满意可以调用
//        reScan();
    }
}
