package com.iboomboom.grace.media;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iboomboom.grace.R;
import com.iboomboom.grace.base.BaseRecyclerAdapter;
import com.iboomboom.grace.utils.ImageUtils;
import com.iboomboom.grace.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * Created by DEVIN on 2017/11/7.
 */

public class PhotoAdapter extends BaseRecyclerAdapter<MediaStoreData> {
    private boolean isMultiSelect;
    private OnPhotoSelectListener mOnPhotoSelectListener;
    private ArrayList<MediaStoreData> selectedItems;

    PhotoAdapter(Context context, int mode, boolean isMultiSelect, ArrayList<MediaStoreData> selectedItems) {
        super(context, mode);
        this.isMultiSelect = isMultiSelect;
        this.selectedItems = selectedItems;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new PhotoHolder(View.inflate(mContext, R.layout.abc_rv_photo_item, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final MediaStoreData item, final int position) {
        PhotoHolder ph = (PhotoHolder) holder;
        ImageUtils.loadImage(item.uri, ph.ivPhoto);
        ph.shadowView.setVisibility(ph.cbPhoto.isChecked() && isMultiSelect ? View.VISIBLE : View.GONE);

        ph.cbPhoto.setOnCheckedChangeListener(null);
        ph.cbPhoto.setChecked((isMultiSelect && selectedItems != null) && selectedItems.contains(item));
        ph.cbPhoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mOnPhotoSelectListener.OnPhotoSelectListener(item, position, buttonView,isChecked);
            }
        });
        ph.cbPhoto.setVisibility(isMultiSelect ? View.VISIBLE : View.GONE);
        ph.shadowView.setVisibility(isMultiSelect && ph.cbPhoto.isChecked() ? View.VISIBLE : View.GONE);

    }

    private class PhotoHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        RelativeLayout.LayoutParams params;
        Float width = (ScreenUtils.getWindowWidth() - 4 * ScreenUtils.dip2px(2f)) / 3f;
        LinearLayout.LayoutParams containerParams;
        RelativeLayout container;
        View shadowView;
        AppCompatCheckBox cbPhoto;

        PhotoHolder(View itemView) {
            super(itemView);
            ivPhoto = new ImageView(mContext);

            params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ivPhoto.setLayoutParams(params);
            ivPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);

            container = itemView.findViewById(R.id.rl_photo_container);
            containerParams = new LinearLayout.LayoutParams(width.intValue(), width.intValue());
            containerParams.leftMargin = containerParams.topMargin = ScreenUtils.dip2px(2f);
            container.setLayoutParams(containerParams);
            container.addView(ivPhoto);

            final View selectView = View.inflate(mContext, R.layout.abc_cb_photo_select, null);
            shadowView = selectView.findViewById(R.id.view_shadow);
            cbPhoto = selectView.findViewById(R.id.cb_photo_select);
            container.addView(selectView);


        }
    }

    public interface OnPhotoSelectListener {
        void OnPhotoSelectListener(MediaStoreData item, int position, CompoundButton buttonView, boolean isChecked);
    }

    public void setOnPhotoSelectListener(OnPhotoSelectListener listener) {
        this.mOnPhotoSelectListener = listener;
    }

}
