package com.zhuoxin.news.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhuoxin.news.db.NewsHelper;
import com.zhuoxin.news.entity.NewsJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nh on 2017/1/12.
 * 数据库的工具类
 */

public class DBUtil {
    /**
     * 此方法用来插入新闻数据
     * @param context 上下文 环境
     * @param news  要插入的数据
     */
    public static  void insertNews(Context context,NewsJson.ResultBean.DataBean news){
        String title=news.getTitle();
        String date=news.getDate();
        String category=news.getCategory();
        String imageURL=news.getThumbnail_pic_s();
        String url=news.getUrl();

        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("date",date);
        values.put("category",category);
        values.put("imageURL",imageURL);
        values.put("url",url);
        //获取数据库
        NewsHelper newshelper =new NewsHelper(context,"News.db",null,1);
        SQLiteDatabase db=newshelper.getReadableDatabase();
        //存储之前，先查询数据库中是否存在该数据
        //如果不存在，执行插入操作

        if(!queryNews(context,news)){
            db.insert("news",null,values);
        }
        db.close();
    }

    /**
     * 查找数据库中对应的新闻数据
     * @param context 上下文
     * @param news 要查找的新闻数据
     * @return  要插入的新闻数据
     */
    public static  boolean queryNews(Context context, NewsJson.ResultBean.DataBean news){
        //获取数据库
        NewsHelper newsHelper =new NewsHelper(context,"News.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
        //根据Url,查询数据
        Cursor cursor=db.query("news",null,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            //获取数据库中的URL
            String url=cursor.getString(cursor.getColumnIndex("url"));
           String newsURL=news.getUrl();
            if(url.equals(newsURL)){
                cursor.close();
                db.close();
                return true;
            }
        }
        //没查到该新闻数据，返回FALSE
        cursor.close();
        db.close();
        return  false;
    }
    public  static List<NewsJson.ResultBean.DataBean> quertAllNews(Context context) {
        List<NewsJson.ResultBean.DataBean> dataBeanList = new ArrayList<>();
        NewsHelper newsHelper = new NewsHelper(context, "News.db", null, 1);
        SQLiteDatabase db = newsHelper.getReadableDatabase();
        //根据Url,查询数据
        Cursor cursor = db.query("news", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取数据库中存储的新闻信息
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            String category=cursor.getString(cursor.getColumnIndex("category"));
            String imageURL=cursor.getString(cursor.getColumnIndex("imageURL"));
            String url=cursor.getString(cursor.getColumnIndex("url"));
            //把基本信息封装成news
            NewsJson.ResultBean.DataBean news=new NewsJson.ResultBean.DataBean();
            news.setTitle(title);
            news.setDate(date);
            news.setCategory(category);
            news.setThumbnail_pic_s(imageURL);
            news.setUrl(url);
            //把news放入List中
            dataBeanList.add(news);

        }
        //没查到该新闻数据，返回FALSE
        cursor.close();
        db.close();
        return  dataBeanList;
    }

    /**
     * 删除数据库的操作
     * @param context
     * @param news
     */
    public static void deleteNews(Context context, NewsJson.ResultBean.DataBean news){

        NewsHelper newsHelper = new NewsHelper(context, "News.db", null, 1);
        SQLiteDatabase db = newsHelper.getReadableDatabase();
        //根据Url,删除数据
        db.delete("news","url=?",new String[]{news.getUrl()});
        //没有查到该新闻数据，返回false
        db.close();


    }
}
