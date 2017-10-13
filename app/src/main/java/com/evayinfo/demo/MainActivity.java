package com.evayinfo.demo;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.evayinfo.grace.base.activity.BackActivity;
import com.evayinfo.grace.base.fragment.BaseListFragment;

import java.nio.Buffer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BackActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.vp)
    ViewPager vp;
    private DemoPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this, this);
        adapter = new DemoPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        BaseListFragment fragment;
        fragment = (BaseListFragment) adapter.getItem(position);
        fragment.clickTitleBackTop("标题");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {

    }
}
