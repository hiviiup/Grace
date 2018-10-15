package com.evayinfo.demo;


import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.activity.BackActivity;
import com.evayinfo.grace.base.activity.BaseActivity;
import com.evayinfo.grace.base.activity.BaseListActivity;
import com.evayinfo.grace.media.MediaHelper;
import com.evayinfo.grace.media.MediaStoreData;
import com.evayinfo.grace.media.MediaStoreDataLoader;
import com.evayinfo.grace.media.PhotoSelectActivity;
import com.evayinfo.grace.media.PhotoSelectConfig;
import com.evayinfo.grace.utils.AppUtils;
import com.evayinfo.grace.utils.FileUtils;
import com.evayinfo.grace.utils.LogUtils;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BackActivity {


    @Override
    public void init() {
        super.init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this, this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
