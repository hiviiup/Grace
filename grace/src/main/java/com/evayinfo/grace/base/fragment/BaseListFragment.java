package com.evayinfo.grace.base.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.evayinfo.grace.R;
import com.evayinfo.grace.base.BaseRecyclerAdapter;
import com.evayinfo.grace.base.activity.BaseActivity;
import com.evayinfo.grace.view.BackTopRecyclerView;
import com.evayinfo.grace.view.RecyclerRefreshLayout;

/**
 * Created by DEVIN on 2017/9/25.
 */

public abstract class BaseListFragment extends BaseFragment implements BackTopRecyclerView.OnRecyclerViewScollToTopListener {

    public BackTopRecyclerView mRecyclerView;
    public FloatingActionButton mFloadtionActionButton;
    public RecyclerRefreshLayout mRefreshLayout;
    private BaseActivity parentActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.abc_activity_base_recyclerview;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getAdapter() != null) {
            mRecyclerView.setAdapter(getAdapter());
        }

        mFloadtionActionButton = view.findViewById(R.id.fab);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setEnabled(false);
    }

    /**
     * 显示返回顶部按钮
     */
    protected void clickButtonBackTop() {
        mRecyclerView.setFloatingButton(mFloadtionActionButton);
    }

    /**
     * 点击activity的标题返回顶部
     * 备注： 当fragment在viewpager中使用时,
     * 需要监听{@link ViewPager#addOnPageChangeListener(ViewPager.OnPageChangeListener)}
     * 并在onPageSelected方法中调用本方法。
     *
     * @param title 标题
     */
    public void clickTitleBackTop(String title) {
        mRecyclerView.setOnScrollToTopListener(this);
        parentActivity = (BaseActivity) getActivity();
        parentActivity.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.scrollToPosition(0);
            }
        });
        this.title = title;
        parentActivity.setTitle(this.title);
    }

    /**
     * 开启下拉刷新功能
     */
    protected void needRefresh(RecyclerRefreshLayout.SuperRefreshLayoutListener listener) {
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setSuperRefreshLayoutListener(listener);
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

    protected void setCanLoad(boolean canLoad) {
        mRefreshLayout.setCanLoadMore(canLoad);
    }

    protected abstract BaseRecyclerAdapter getAdapter();

    /**
     * 加载数据
     */
    protected void requestData() {

    }


    @Override
    public void onScrollToTop() {
        parentActivity.setTitle("点击标题返回顶部");
    }

    @Override
    public void onScrollIdle() {
        parentActivity.setTitle(title);
    }
}
