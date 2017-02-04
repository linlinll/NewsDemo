package com.zhuoxin.news.Frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.news.Adpter.ImageAdaptr;
import com.zhuoxin.news.R;
import com.zhuoxin.news.retorfit.ImageApi;
import com.zhuoxin.news.utils.JsonImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nh on 2017/1/6.
 */

public class ImageFrament extends Fragment {

    @BindView(R.id.rv_image)
    RecyclerView rv_image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_image, container, false);
        ButterKnife.bind(this, view);
        //2构建Retrofit工具
        Retrofit retrofit =new Retrofit
                .Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //3用retrofit构建真正的接口
        ImageApi imageApi=retrofit.create(ImageApi.class);
        //4.根据不同的网址，获取call结果
       Call<JsonImage> getImageCall =imageApi.getResponse();
        //5.加入队列，处理成功或失败
        getImageCall.enqueue(new Callback<JsonImage>() {
            @Override
            public void onResponse(Call<JsonImage> call, Response<JsonImage> response) {
                //获取真正的内容
                JsonImage jsonImage=response.body();
                //获取LIST数据
                List<JsonImage.ResultsBean> resultsBeen=jsonImage.getResults();
                //放入RecyclerView中
                ImageAdaptr imageAdaptr=new ImageAdaptr(getActivity(),resultsBeen);
                StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                rv_image.setLayoutManager(layoutManager);
                rv_image.setAdapter(imageAdaptr);
            }

            @Override
            public void onFailure(Call<JsonImage> call, Throwable t) {

            }
        });

        return view;


    }
}
