package com.evayinfo.grace.base.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.evayinfo.grace.R;
import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.RefreshType;
import com.evayinfo.grace.view.BackTopRecyclerView;
import com.evayinfo.grace.view.RecyclerRefreshLayout;

/**
 * Created by Devin on 2017/8/16.
 */

public abstract class BaseListActivity extends BackActivity implements BackTopRecyclerView.OnRecyclerViewScollToTopListener {

    RecyclerRefreshLayout mRefreshLayout;
    BackTopRecyclerView mRecyclerView;
    FloatingActionButton mFab;
    String title;
    public int page = 1;
    public boolean isRefreshing = true;

    @Override
    public int getLayoutId() {
        return R.layout.abc_activity_base_recyclerview;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        super.initView();

        mRefreshLayout = findViewById(R.id.refresh_layout);
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.rv);

        mRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(getLayoutManager());
        if (getAdapter() != null) mRecyclerView.setAdapter(getAdapter());
    }

    @Override
    protected void initData() {
        super.initData();
        requestData(RefreshType.PULL_TO_REFRESH);
    }

    /**
     * 列表使用分页加载和下拉刷新功能
     */
    public void setRefresh() {
        mRefreshLayout.setCanLoadMore(true);
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setSuperRefreshLayoutListener(new RecyclerRefreshLayout.SuperRefreshLayoutListener() {
            @Override
            public void onRefreshing() {
                requestData(RefreshType.PULL_TO_REFRESH);
            }

            @Override
            public void onLoadMore() {
                requestData(RefreshType.LOAD_MORE);
            }
        });
    }


    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 刷新完成
     *
     * @param isFinish 是否完成
     */
    protected void setRefreshing(boolean isFinish) {
        mRefreshLayout.setRefreshing(isFinish);
    }

    protected void setLoading(boolean isLoad) {
        mRefreshLayout.setOnLoading(isLoad);
    }


    protected abstract BaseRecyclerAdapter getAdapter();

    /**
     * 加载数据
     */
    protected void requestData(RefreshType refreshType) {

    }

    @Override
    public void onScrollToTop() {
        setTitle("点击标题返回顶部");
    }

    @Override
    public void onScrollIdle() {
        setTitle(title);
    }


    protected void clickFabBackTop() {
        mRecyclerView.setFloatingButton(mFab);
        mFab.show();
    }

    protected void clickTitleBackTop(String title) {
        mRecyclerView.setOnScrollToTopListener(this);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.scrollToPosition(0);
            }
        });
        this.title = title;
    }

}
