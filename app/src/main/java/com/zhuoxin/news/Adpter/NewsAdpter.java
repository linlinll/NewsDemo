package com.zhuoxin.news.Adpter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhuoxin.news.Activity.NewsDataActivity;
import com.zhuoxin.news.R;
import com.zhuoxin.news.db.NewsHelper;
import com.zhuoxin.news.entity.News;
import com.zhuoxin.news.entity.NewsJson;
import com.zhuoxin.news.utils.BitmapAsyncTask;
import com.zhuoxin.news.utils.DBUtil;
import com.zhuoxin.news.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nh on 2017/1/9.
 */

public class NewsAdpter extends RecyclerView.Adapter<NewsAdpter.ViewHolder> {
    //1.创建Context,数据，并在构造函数中初始化
    Context context;
    List<NewsJson.ResultBean.DataBean> newsList = new ArrayList<>();
    Activity activity;
    boolean isNewsFragment;//设置一个标记，来判断是否是NewsFragment


    public NewsAdpter(Context context, List<NewsJson.ResultBean.DataBean> newsList, Activity activity, boolean isNewsFragment) {
        this.context = context;
        this.newsList = newsList;
        this.activity = activity;
        this.isNewsFragment = isNewsFragment;
    }

    //4.填充布局，把布局填充到VIEWHolder中
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //填充itemView布局
        View itemview = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        //把填充好的布局，传递到ViewHolder中
        ViewHolder viewHolder = new ViewHolder(itemview);

        return viewHolder;
    }

    //绑定试图，展示数据
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final NewsJson.ResultBean.DataBean news = newsList.get(position);
        //设置item的单机事件
        View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, news.getTitle(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("url", news.getUrl());
                bundle.putString("image", news.getThumbnail_pic_s());
                bundle.putString("title", news.getTitle());
                Intent intent = new Intent(activity, NewsDataActivity.class);
                intent.putExtra("Bundle", bundle);
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, holder.iv_news, "newsTransition").toBundle());
            }
        });
        //设置item的长机事件
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isNewsFragment) {

                    //AlterDialog的使用+SQLite的创建和使用
                    AlertDialog alertDialog = new AlertDialog.Builder(context).
                            setTitle("收藏提醒")
                            .setMessage("您确定收藏该消息么")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //确定操作，涉及到把新闻数据存储到数据库中
                                    //插入新闻数据
                                    DBUtil.insertNews(context, news);
                                }
                            }).create();
                    alertDialog.show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).
                            setTitle("删除提醒")
                            .setMessage("您确定删除该消息么")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //确定操作，涉及到把新闻数据存储到数据库中
                                    //1.删除新闻数据
                                    DBUtil.deleteNews(context,news);
                                    //2.list中的数据
                                    newsList.remove(news);
                                    //3.刷新recyclerview的界面
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position,newsList.size());
                                }
                            }).create();
                    alertDialog.show();

                }
                return true;
            }
        });
        holder.tv_title.setText(news.getTitle());
        holder.tv_date.setText(news.getDate());
        holder.tv_category.setText(news.getCategory());
        holder.iv_news.setImageResource(R.mipmap.ic_launcher);
//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                Bitmap bitmap= (Bitmap) msg.obj;
//                holder.iv_news.setImageBitmap(bitmap);
//            }
//        };
//        //开辟子线程中的图片
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //获取图片
//                final Bitmap bitmap= HttpUtil.getBitmap(news.getThumbnail_pic_s());
//                //发送到主线程
//               Message message=handler.obtainMessage();
//                message.obj=bitmap;
//                handler.sendMessage(message);
//            }
//        }).start();


        //实例化BitmapAsyncTask
        BitmapAsyncTask bitmapAsyncTask = new BitmapAsyncTask(holder.iv_news);
        //开启线程执行操作
        bitmapAsyncTask.execute(news.getThumbnail_pic_s());

        //用 Picasso加载图片
        Picasso.with(context)
                .load(news.getThumbnail_pic_s())
                .error(R.mipmap.ic_launcher)
                .into(holder.iv_news);
    }

    //3.计数
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    //2.创建ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_news;
        TextView tv_title;
        TextView tv_date;
        TextView tv_category;

        //把外部填充好的itemView拿过来
        public ViewHolder(View itemView) {
            super(itemView);
            //把ViewHolder中的控件初始化
            iv_news = (ImageView) itemView.findViewById(R.id.iv_news);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_category = (TextView) itemView.findViewById(R.id.tv_category);

        }
    }
}
