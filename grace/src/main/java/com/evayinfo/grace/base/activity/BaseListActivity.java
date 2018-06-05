package com.evayinfo.grace.base.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;


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
    RelativeLayout rlTopContainer;
    String title;
    private int page = 1;
    private int totalPage = 0;
    private boolean isRefreshing = true;

    @Override
    public int getLayoutId() {
        return R.layout.abc_activity_base_recyclerview;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    public void initView() {
        super.initView();

        rlTopContainer = findViewById(R.id.rl_top_container);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.rv);

        mRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(getLayoutManager());
        if (getAdapter() != null) mRecyclerView.setAdapter(getAdapter());
    }


    public void addTopView(View view) {
        rlTopContainer.addView(view);
    }

    @Override
    public void initData() {
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
                if (isRefreshing) return;
                isRefreshing = true;
                requestData(RefreshType.PULL_TO_REFRESH);
            }

            @Override
            public void onLoadMore() {
                if (page == totalPage) {
                    onRequestComplete();
                    return;
                }
                if (isRefreshing) return;
                isRefreshing = true;
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


    protected abstract BaseRecyclerAdapter getAdapter();

    /**
     * 加载数据
     */
    protected void requestData(RefreshType refreshType) {

    }

    public void setTotalPage(int pageNum) {
        totalPage = pageNum;
    }

    public void onRequestComplete() {
        isRefreshing = false;
        mRefreshLayout.onComplete();
    }


    /**
     * 递增页数
     *
     * @param refreshType
     * @return
     */
    public int nextPage(RefreshType refreshType) {
        page = refreshType == RefreshType.PULL_TO_REFRESH ? 1 : page + 1;
        return page;
    }

    /**
     * 加载错误时，返回上一个页码
     *
     * @return
     */
    public int backPrePage() {
        page = page - 1;
        return page;
    }

    @Override
    public void onScrollToTop() {
        setTitle("点击标题返回顶部");
    }

    @Override
    public void onScrollIdle() {
        setTitle(title);
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
