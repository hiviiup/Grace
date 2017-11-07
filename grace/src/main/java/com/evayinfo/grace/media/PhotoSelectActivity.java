package com.evayinfo.grace.media;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.evayinfo.grace.R;
import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.activity.BaseListActivity;
import com.evayinfo.grace.utils.AppUtils;
import com.evayinfo.grace.utils.ScreenUtils;

import java.util.List;

/**
 * Created by DEVIN on 2017/11/7.
 * 图片选择器，支持单选和多选图片
 */

public class PhotoSelectActivity extends BaseListActivity
        implements LoaderManager.LoaderCallbacks<List<MediaStoreData>>,
        BaseRecyclerAdapter.OnItemClickListener {

    private PhotoAdapter photoAdapter;
    private boolean isMultiSelect;

    public static void show(Context context, boolean isMultiSelected) {
        Intent intent = new Intent(context, PhotoSelectActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("multi", isMultiSelected);
        context.startActivity(intent);
    }


    @Override
    protected void init() {
        super.init();
        getSupportLoaderManager().initLoader(R.id.loader_media_store_data_load, null, this);
    }

    @Override
    protected void initBundles() {
        super.initBundles();
        isMultiSelect = getIntent().getBooleanExtra("multi", false);
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

        if (isMultiSelect) {
            //当选择多张图片时，显示底部菜单按钮
            final LinearLayout rootView = findViewById(R.id.ll_activity_base_view);
            final View selectMenuView = View.inflate(this, R.layout.abc_activity_photo_select_foot, null);
            rootView.addView(selectMenuView);
        }
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        photoAdapter = new PhotoAdapter(this, BaseRecyclerAdapter.NEITHER, isMultiSelect);
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

    }
}
