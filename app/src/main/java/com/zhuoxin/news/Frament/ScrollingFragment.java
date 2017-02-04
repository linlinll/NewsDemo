package com.zhuoxin.news.Frament;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.zhuoxin.news.Adpter.ScrollingAdapter;
import com.zhuoxin.news.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nh on 2017/1/11.
 * 含有ViewPager可以左右滑动的Fragment
 */


public class ScrollingFragment extends Fragment {
    //ButterKnife黄油刀  用来寻找View和设置各种单机事件
    //绑定需要的VIews试图
    @BindView(R.id.viewpager)
    ViewPager view_pager;
    //创建ScrollingAdapter
    ScrollingAdapter scrollingAdapter;
    //创建数据
    List<Fragment> fragmentList;

    TableLayout tableLayout;
    @BindView(R.id.tblayout)
    TabLayout tblayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_scrolling, container, false);
        //1.初始化ButterKnife
        ButterKnife.bind(this, view);
        //初始化各种数据，设置到ViewPager上
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFrament("top"));
        fragmentList.add(new NewsFrament("yule"));
        fragmentList.add(new NewsFrament("keji"));
        fragmentList.add(new NewsFrament("youxi"));
        scrollingAdapter = new ScrollingAdapter(getChildFragmentManager(), fragmentList);
        view_pager.setAdapter(scrollingAdapter);
        //设置TabLayout
        tblayout.setupWithViewPager(view_pager);
        return view;
    }
}
