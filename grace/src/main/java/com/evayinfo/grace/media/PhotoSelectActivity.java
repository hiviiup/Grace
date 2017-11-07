package com.evayinfo.grace.media;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.evayinfo.grace.R;
import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.activity.BaseListActivity;
import com.evayinfo.grace.utils.AppUtils;

import java.util.List;

/**
 * Created by DEVIN on 2017/11/7.
 */

public class PhotoSelectActivity extends BaseListActivity
        implements LoaderManager.LoaderCallbacks<List<MediaStoreData>>, BaseRecyclerAdapter.OnItemClickListener {

    private PhotoAdapter photoAdapter;

    public static void show(Context context) {
        Intent intent = new Intent(context, PhotoSelectActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void init() {
        super.init();
        getSupportLoaderManager().initLoader(R.id.loader_media_store_data_load, null, this);
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("选择图片");
        setBack(true);
    }

    @Override
    protected void initView() {
        super.initView();
        getRecyclerView().setBackgroundColor(0xFF353535);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        photoAdapter = new PhotoAdapter(this, BaseRecyclerAdapter.NEITHER);
        photoAdapter.setOnItemClickListener(this);
        return photoAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3);
    }

    @Override
    public Loader<List<MediaStoreData>> onCreateLoader(int id, Bundle args) {
        return new MediaStoreDataLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<MediaStoreData>> loader, List<MediaStoreData> data) {
        getSupportLoaderManager().destroyLoader(R.id.loader_media_store_data_load);
        photoAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<MediaStoreData>> loader) {
    }

    @Override
    public void onItemClick(int position, long itemId) {
        AppUtils.toast(photoAdapter.getItem(position).uri.toString());
    }
}
