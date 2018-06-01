package com.evayinfo.grace.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.evayinfo.grace.R;


/**
 * Created by Devin on 2017/8/16.
 * 基础BaseActivity类
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;
    public LinearLayout llRootLayout;
    public LinearLayout llContentLayout;
    private RelativeLayout rlProgressbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(R.layout.abc_activity_base);
        }

        llContentLayout = findViewById(R.id.ll_activity_view_container);
        llRootLayout = findViewById(R.id.ll_activity_base_view);

        rlProgressbar = findViewById(R.id.rl_progressbar);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
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
    public void init() {
    }

    /**
     * 设置布局ID
     *
     * @return 返回布局id
     */
    public abstract int getLayoutId();

    /**
     * 初始化Extras
     */
    public void initBundles() {

    }

    /**
     * 初始化标题栏
     */
    public void initTitleBar() {
        toolbar = findViewById(R.id.title_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    /**
     * 初始化View
     */
    public void initView() {
        ViewGroup activityViewContainer = findViewById(R.id.ll_activity_view_container);
        View.inflate(this, getLayoutId(), activityViewContainer);
    }

    /**
     * 数据处理
     */
    public void initData() {

    }

    /**
     * 设置监听事件
     */
    public void addListener() {

    }

    /**
     * 判断是否拥有actionbar
     *
     * @return
     */
    public boolean hasActionbar() {
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

    @Override
    public void setTitle(int titleId) {
        if (hasActionbar()) {
            {
                final ActionBar supportActionBar = getSupportActionBar();
                if (supportActionBar != null) {
                    supportActionBar.setTitle(titleId);
                }
            }
        }
    }

    @Override
    public void setTitle(CharSequence title) {
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
    public void setBack(boolean isBack) {
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

    public void showProgress() {
        rlProgressbar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        rlProgressbar.setVisibility(View.GONE);
    }

}
