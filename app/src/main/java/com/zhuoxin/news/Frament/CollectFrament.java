package com.zhuoxin.news.Frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.news.Adpter.NewsAdpter;
import com.zhuoxin.news.R;
import com.zhuoxin.news.entity.NewsJson;
import com.zhuoxin.news.utils.DBUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nh on 2017/1/6.
 */

public class CollectFrament extends Fragment {
    @BindView(R.id.rv_collect)
    RecyclerView rv_collect;
    List<NewsJson.ResultBean.DataBean> dataBeanList;
    NewsAdpter newsAdpter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_collect, container, false);
        ButterKnife.bind(this, view);
        //从数据中查找数据
        dataBeanList = DBUtil.quertAllNews(getContext());
        //创建adapter
        newsAdpter=new NewsAdpter(getContext(),dataBeanList,getActivity(),false);
        //关联  recycleview
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv_collect.setLayoutManager(layoutManager);
        rv_collect.setAdapter(newsAdpter);
        return view;
    }
}
