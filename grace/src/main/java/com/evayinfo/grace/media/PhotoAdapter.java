package com.evayinfo.grace.media;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.evayinfo.grace.R;
import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.utils.ImageUtils;
import com.evayinfo.grace.utils.ScreenUtils;

/**
 * Created by DEVIN on 2017/11/7.
 */

public class PhotoAdapter extends BaseRecyclerAdapter<MediaStoreData> {
    public PhotoAdapter(Context context, int mode) {
        super(context, mode);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new PhotoHolder(View.inflate(mContext, R.layout.abc_activity_photo_item, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, MediaStoreData item, int position) {
        PhotoHolder ph = (PhotoHolder) holder;
        ImageUtils.loadImage(item.uri, ph.ivPhoto);
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        RelativeLayout.LayoutParams params;
        Float width = (ScreenUtils.getWindowWidth() - 4 * ScreenUtils.dip2px(2f)) / 3f;

        PhotoHolder(View itemView) {
            super(itemView);
            ivPhoto = new ImageView(mContext);

            params = new RelativeLayout.LayoutParams(width.intValue(), width.intValue());
            ivPhoto.setLayoutParams(params);
            ivPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final RelativeLayout container = itemView.findViewById(R.id.rl_photo_container);
            container.addView(ivPhoto);
        }
    }
}
