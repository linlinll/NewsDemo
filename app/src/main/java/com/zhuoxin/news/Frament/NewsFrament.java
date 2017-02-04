package com.zhuoxin.news.Frament;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhuoxin.news.Adpter.NewsAdpter;
import com.zhuoxin.news.R;
import com.zhuoxin.news.entity.NewsJson;
import com.zhuoxin.news.utils.HttpUtil;
import com.zhuoxin.news.utils.JsonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nh on 2017/1/6.
 */

public class NewsFrament extends Fragment {
    RecyclerView recyclerView;
    NewsAdpter newsAdpter;
    List<NewsJson.ResultBean.DataBean> newsList;
    boolean isNewsFragment;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    //设置布局管理器
                       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);
                      //设置Adapter
                      recyclerView.setAdapter(newsAdpter);
                    Toast.makeText(getContext(), "请求数据成功", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            srl_new.setRefreshing(false);
        }
    }   ;
    String type;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl_new;

    public NewsFrament() {
    }

    public NewsFrament(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //填充布局
        View view = inflater.inflate(R.layout.frament_news, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        ButterKnife.bind(this, view);
        return view;
    }

    //当Activity完成创建shi，执行相应操作
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srl_new.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //重新从服务器获取数据
                loadMessage();
            }
        });
        loadMessage();
    }

    /**
     * 加载网络信息
     */
    private void loadMessage() {
        //子线程，获取网络数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                //访问服务器，获得Json数据
                final String urlStr = "http://v.juhe.cn/toutiao/index?type=" + type + "&key=d728ab4e75e137c4f23aec12ed3ee6cd";
                //回调接口
                HttpUtil.HttpCallBack callBack = new HttpUtil.HttpCallBack() {
                    @Override
                    public void callBackSuccess(String jsonStr) {
                        //解析Json数据，返回一份newList
                        newsList = JsonUtil.parseJsonWithGson(jsonStr);
                        //创建Adapter
                        newsAdpter = new NewsAdpter(getActivity(), newsList, getActivity(),true);
                        //把Adapter绑定到RecycleView中，刷新UI
                         handler.sendEmptyMessage(0);

                            }


                    @Override
                    public void callBackFailed() {
                        //发送空消息
                        handler.sendEmptyMessage(1);
                    }
                };
                //如果网络速度很慢。则获取不到数据
                HttpUtil.getNewsInfo(urlStr, callBack);
            }
        }).start();
    }
}

