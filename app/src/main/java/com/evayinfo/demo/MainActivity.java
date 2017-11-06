package com.evayinfo.demo;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.activity.BaseListActivity;
import com.evayinfo.grace.media.MediaHelper;
import com.evayinfo.grace.media.MediaStoreData;
import com.evayinfo.grace.media.MediaStoreDataLoader;

import java.util.List;
import java.util.logging.Logger;

import butterknife.ButterKnife;


public class MainActivity extends BaseListActivity implements LoaderCallbacks<List<MediaStoreData>> {

    private DemoAdapter demoAdapter;

    @Override
    protected void init() {
        super.init();
        getSupportLoaderManager().initLoader(R.id.loader_media_store_data_load, null, this);
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

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        demoAdapter = new DemoAdapter(this, BaseRecyclerAdapter.NEITHER);
        return demoAdapter;
    }


    @Override
    public Loader<List<MediaStoreData>> onCreateLoader(int id, Bundle args) {
        return new MediaStoreDataLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<MediaStoreData>> loader, List<MediaStoreData> data) {
        demoAdapter.addAll(data);
        getSupportLoaderManager().destroyLoader(R.id.loader_media_store_data_load);
    }

    @Override
    public void onLoaderReset(Loader<List<MediaStoreData>> loader) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
