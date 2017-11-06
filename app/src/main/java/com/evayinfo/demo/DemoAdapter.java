package com.evayinfo.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.media.MediaStoreData;
import com.evayinfo.grace.utils.ImageUtils;
import com.evayinfo.grace.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DEVIN on 2017/9/22.
 */

public class DemoAdapter extends BaseRecyclerAdapter<MediaStoreData> {
    public DemoAdapter(Context context, int mode) {
        super(context, mode);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new DemoHolder(View.inflate(mContext, R.layout.rv_item, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, MediaStoreData item, int position) {
        DemoHolder mHolder = (DemoHolder) holder;
        LogUtils.e(item.toString());
        ImageUtils.loadImage(item.uri, mHolder.tv);
    }

    public class DemoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        ImageView tv;

        public DemoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
