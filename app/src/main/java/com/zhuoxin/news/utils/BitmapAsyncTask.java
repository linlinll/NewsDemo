package com.zhuoxin.news.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by nh on 2017/1/10.
 */

public class BitmapAsyncTask extends AsyncTask<String,Void,Bitmap> {
   ImageView imageView;

    public BitmapAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }

    //doInBackground 方法是在子线程中执行，主要用来执行好事操作
    @Override
    protected Bitmap doInBackground(String... params) {
       //获取0的位置参数，进行网络图片的加载
        return  HttpUtil.getBitmap(params[0]);
    }

    //主线程的操作，用来刷新UI

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //执行自己想要的操作：把bitmap显示到ImageView
        imageView.setImageBitmap(bitmap);
    }
}
