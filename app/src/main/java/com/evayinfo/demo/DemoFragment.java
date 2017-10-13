package com.evayinfo.demo;

import android.view.View;

import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.fragment.BaseListFragment;

/**
 * Created by DEVIN on 2017/10/13.
 */

public class DemoFragment extends BaseListFragment {
    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new DemoAdapter(getContext(),BaseRecyclerAdapter.NEITHER);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }
}
