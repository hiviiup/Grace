package com.iboomboom.grace.base.activity;

/**
 * Created by Devin on 2017/8/16.
 * 带返回功能的activity
 */

public abstract class BackActivity extends BaseActivity {

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setBack(true);
    }
}
