package com.zhuoxin.news.Adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by nh on 2017/1/11.
 */

public class ScrollingAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    String pageTitle[]=new String[]{"头条","科技","游戏","国际"};
    public ScrollingAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
