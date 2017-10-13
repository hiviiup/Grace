package com.evayinfo.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evayinfo.grace.base.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DEVIN on 2017/9/22.
 */

public class DemoAdapter extends BaseRecyclerAdapter<String> {
    List<String> strings;

    public DemoAdapter(Context context, int mode) {
        super(context, mode);
        strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add("这是一条Item");
        }
        addAll(strings);
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
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        DemoHolder mHolder = (DemoHolder) holder;
        mHolder.tv.setText(getItems().get(position));
    }

    public class DemoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        public DemoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
