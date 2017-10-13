package com.evayinfo.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.evayinfo.grace.base.fragment.BaseListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DEVIN on 2017/10/13.
 */

class DemoPagerAdapter extends FragmentPagerAdapter {

    private final List<BaseListFragment> fragments;

    public DemoPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new DemoFragment());
        fragments.add(new DemoFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
