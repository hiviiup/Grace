package com.evayinfo.grace.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.evayinfo.grace.R;

/**
 * Created by Devin on 2017/8/16.
 * 基础BaseActivity类
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(R.layout.abc_activity_base);
        }

        init();
        initBundles();
        initTitleBar();
        initView();
        initData();
        addListener();
    }

    /**
     * 初始化一些配置信息
     */
    protected void init() {
    }

    /**
     * 设置布局ID
     *
     * @return 返回布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化Extras
     */
    protected void initBundles() {

    }

    /**
     * 初始化标题栏
     */
    protected void initTitleBar() {
        toolbar = findViewById(R.id.title_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    /**
     * 初始化View
     */
    protected void initView() {
        ViewGroup rootView = findViewById(R.id.rl_root_view);
        final View contentView = View.inflate(this, getLayoutId(), null);
        rootView.addView(contentView);
    }

    /**
     * 数据处理
     */
    protected void initData() {

    }

    /**
     * 设置监听事件
     */
    protected void addListener() {

    }

    /**
     * 判断是否拥有actionbar
     *
     * @return
     */
    protected boolean hasActionbar() {
        return getSupportActionBar() != null;
    }

    /**
     * 设置标题栏标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        if (hasActionbar()) {
            {
                final ActionBar supportActionBar = getSupportActionBar();
                if (supportActionBar != null) {
                    supportActionBar.setTitle(title);
                }
            }
        }
    }


    /**
     * 设置标题栏返回按钮
     *
     * @param isBack 是否需要返回按钮
     */
    protected void setBack(boolean isBack) {
        if (hasActionbar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isBack);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
