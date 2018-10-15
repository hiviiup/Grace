package com.iboomboom.grace.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Devin on 2017/9/1.
 */

public abstract class BaseFragment extends Fragment {

    public String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (getLayoutId() != 0)
            view = View.inflate(getContext(), getLayoutId(), null);
        else
            throw new RuntimeException("请先设置布局");

        initBundle();
        initView(view);
        initData();

        return view;
    }


    /**
     * 设置Fragment布局
     *
     * @return 返回布局
     */
    protected abstract int getLayoutId();


    /**
     * 初始化控件
     *
     * @param view view
     */
    protected void initView(View view) {

    }

    /**
     * 初始化参数
     */
    protected void initBundle() {

    }

    /**
     * 网络数据加载
     */
    protected void initData() {

    }


}
