package com.zhuoxin.news.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by nh on 2017/1/6.
 * 网络访问工具类
 */

public class HttpUtil {
    //通过接口回调来执行
    public  interface  HttpCallBack{
        public  void callBackSuccess(String jsonStr);
        public  void  callBackFailed();
    }

    //获取里面的数据
    public static void  getNewsInfo(String url,HttpCallBack callBack){
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        HttpURLConnection httpURLConnection;
        try {
            //1.根据接口创建URL
            URL targetURl=new URL(url);
            //2.根据URL建立HttpURLConnection
            httpURLConnection= (HttpURLConnection) targetURl.openConnection();
            //3.设置HttpURLConnectionc参数
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            //4.解析服务器返回的数据
            httpURLConnection.connect();
            //5.获取服务器的响应码
            int responseCode=httpURLConnection.getResponseCode();
            if(responseCode==200){
                //正常响应状态为200
                //6.获取服务器的字节流，并解析
                inputStream =httpURLConnection.getInputStream();
                inputStreamReader =new InputStreamReader(inputStream);
                bufferedReader =new BufferedReader(inputStreamReader);
                //从BufferedReader中，读取所有的数据
                StringBuffer stringBuffer=new StringBuffer();
                String msg;
                while ((msg=bufferedReader.readLine())!=null){
                    stringBuffer.append(msg);
                }
                callBack.callBackSuccess(stringBuffer.toString());

            }else{
                System.out.println("获取数据失败");
                callBack.callBackFailed();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
               //如果服务器不为空，关闭所有流
                try {
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //定义LruCache
    private static LruCache<String ,Bitmap> bitmapLruCache
            =new LruCache<String, Bitmap>((int)
            (Runtime.getRuntime().maxMemory()/8)){


        @Override
        protected int sizeOf(String key, Bitmap value) {
           //获取每一张图片的大小
            return value.getHeight()*value.getRowBytes();
        }
    };

    public  static Bitmap getBitmap(String imgURL){
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;
        //从LruCache中取图片
        Bitmap bitmap=bitmapLruCache.get(imgURL);
        if(bitmap!=null){
            //如果取出了图片数据，则返回该图片
            return  bitmap;
        }else {
            //如果没有图片，从网络加载并返回
            try {
                //1.建立URL
                URL url = new URL(imgURL);
                //2.建立HttpURLConnection
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //3.设置参数，判断响应码是否为200
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                //4.取出服务器数据
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    //取出图片
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    //把图片存入LruCache中
                    bitmapLruCache.put(imgURL,bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpURLConnection.disconnect();
            }
            //返回图片
            return bitmap;
        }
    }
}
