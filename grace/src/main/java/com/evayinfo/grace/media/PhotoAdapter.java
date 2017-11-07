package com.evayinfo.grace.media;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.evayinfo.grace.R;
import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.utils.ImageUtils;
import com.evayinfo.grace.utils.ScreenUtils;

/**
 * Created by DEVIN on 2017/11/7.
 */

public class PhotoAdapter extends BaseRecyclerAdapter<MediaStoreData> {
    private boolean isMultiSelect;

    PhotoAdapter(Context context, int mode, boolean isMultiSelect) {
        super(context, mode);
        this.isMultiSelect = isMultiSelect;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new PhotoHolder(View.inflate(mContext, R.layout.abc_rv_photo_item, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, MediaStoreData item, int position) {
        PhotoHolder ph = (PhotoHolder) holder;
        ImageUtils.loadImage(item.uri, ph.ivPhoto);
        ph.shadowView.setVisibility(ph.cbPhoto.isChecked() && isMultiSelect ? View.VISIBLE : View.GONE);
        ph.cbPhoto.setVisibility(isMultiSelect ? View.VISIBLE : View.GONE);
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
}
