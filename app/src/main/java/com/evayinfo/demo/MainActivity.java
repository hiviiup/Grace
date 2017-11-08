package com.evayinfo.demo;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.activity.BaseActivity;
import com.evayinfo.grace.base.activity.BaseListActivity;
import com.evayinfo.grace.media.MediaHelper;
import com.evayinfo.grace.media.MediaStoreData;
import com.evayinfo.grace.media.MediaStoreDataLoader;
import com.evayinfo.grace.media.PhotoSelectActivity;
import com.evayinfo.grace.media.PhotoSelectConfig;

import java.util.List;
import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    private DemoAdapter demoAdapter;

    @Override
    protected void init() {
        super.init();
    }

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

    @OnClick(R.id.btn)
    void openPhotoSelected() {
        PhotoSelectActivity.show(this, new PhotoSelectConfig(true, 8));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
