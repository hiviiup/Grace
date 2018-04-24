package com.evayinfo.grace.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.evayinfo.grace.R;
import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.activity.BaseListActivity;
import com.evayinfo.grace.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DEVIN on 2017/11/7.
 * 图片选择器，支持单选和多选图片
 */

public class PhotoSelectActivity extends BaseListActivity
        implements LoaderManager.LoaderCallbacks<List<MediaStoreData>>,
        BaseRecyclerAdapter.OnItemClickListener, PhotoAdapter.OnPhotoSelectListener, View.OnClickListener {

    private static final int REQUEST_PHOTO = 1002;
    private PhotoAdapter photoAdapter;
    private ArrayList<MediaStoreData> selectedMediaStoreData;
    private int maxSelectCount;
    private boolean isMultiSelect;

    public static void show(Activity activity, PhotoSelectConfig config) {
        Intent intent = new Intent(activity, PhotoSelectActivity.class);
        intent.putExtra("multi", config);
        activity.startActivityForResult(intent,REQUEST_PHOTO);
    }

    public static void show(Fragment fragment, PhotoSelectConfig config) {
        Intent intent = new Intent(fragment.getContext(), PhotoSelectActivity.class);
        intent.putExtra("multi", config);
        fragment.startActivityForResult(intent,REQUEST_PHOTO);
    }

    @Override
    public void init() {
        super.init();
        getSupportLoaderManager().initLoader(R.id.loader_media_store_data_load, null, this);
    }

    @Override
    public void initBundles() {
        super.initBundles();
        PhotoSelectConfig config = getIntent().getParcelableExtra("multi");
        isMultiSelect = config.isMultiSelect();
        maxSelectCount = config.getMaxSelectCount();
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("选择图片");
        setBack(true);
    }

    @Override
    public void initView() {
        super.initView();
        getRecyclerView().setBackgroundColor(0xFF353535);

        if (isMultiSelect) {
            //当选择多张图片时，显示底部菜单按钮
            final LinearLayout rootView = findViewById(R.id.ll_activity_base_view);
            final View selectMenuView = View.inflate(this, R.layout.abc_activity_photo_select_foot, null);
            selectMenuView.findViewById(R.id.tv_done).setOnClickListener(this);
            rootView.addView(selectMenuView);
        }
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        if (isMultiSelect && selectedMediaStoreData == null)
            selectedMediaStoreData = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, BaseRecyclerAdapter.NEITHER, isMultiSelect, selectedMediaStoreData);
        photoAdapter.setOnItemClickListener(this);
        photoAdapter.setOnPhotoSelectListener(this);
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
    public void onClick(View v) {
        if (selectedMediaStoreData.size() == 0) {
            AppUtils.toast("您未选择任何图片");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("photos", selectedMediaStoreData);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onItemClick(int position, long itemId) {
        if (isMultiSelect) {
            //查看单张图片预览

        } else {
            Intent intent = new Intent();
            intent.putExtra("photo", photoAdapter.getItem(position));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void OnPhotoSelectListener(MediaStoreData item, int position, CompoundButton buttonView, boolean isChecked) {
        if (isMultiSelect) {
            if (selectedMediaStoreData.size() >= maxSelectCount && isChecked) {
                buttonView.setChecked(false);
                AppUtils.toast("只能选择" + maxSelectCount + "张照片");
                return;
            }
            if (isChecked && !selectedMediaStoreData.contains(item)) {
                selectedMediaStoreData.add(item);
            } else if (!isChecked && selectedMediaStoreData.contains(item)) {
                selectedMediaStoreData.remove(item);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }

}
