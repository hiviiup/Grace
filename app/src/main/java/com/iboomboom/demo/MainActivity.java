package com.iboomboom.demo;


import com.iboomboom.grace.base.activity.BackActivity;

import butterknife.ButterKnife;


public class MainActivity extends BackActivity {


    @Override
    public void init() {
        super.init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this, this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
