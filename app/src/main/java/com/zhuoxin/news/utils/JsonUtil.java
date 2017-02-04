package com.zhuoxin.news.utils;

import com.google.gson.Gson;
import com.zhuoxin.news.entity.News;
import com.zhuoxin.news.entity.NewsJson;
import com.zhuoxin.news.entity.NewsJsonBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nh on 2017/1/9.
 */

public class JsonUtil {
    //解析Json数据，获取最内层的新闻
    public static List<News> parseJson(String str){
        List<News> newsList=new ArrayList<>();
        try {
            //JsonObject JsonArray
            //1.构建最外层的Json对象
            JSONObject jsonObject=new JSONObject(str);
            //2.根据层次结构，依次取出Json的所有数据
            //2.1第一层嵌套，取出reason和result
            String reason=jsonObject.getString("reason");
            JSONObject result=jsonObject.getJSONObject("result");
            //2.2第二层嵌套，取stataus和data新闻数据
            String stat=result.getString("stat");
            JSONArray data=result.getJSONArray("data");
            for(int i=0;i<data.length();i++){
                JSONObject newsinfo=data.getJSONObject(i);
                String title=newsinfo.getString("title");
                String uniquekey=newsinfo.getString("uniquekey");
                String date=newsinfo.getString("date");
                String category=newsinfo.getString("category");
                String author_name=newsinfo.getString("author_name");
                String url=newsinfo.getString("url");
                String thumbnail_pic_s=newsinfo.getString("thumbnail_pic_s");
                //把数据存放到News中
                News news=new News( title,  uniquekey, date,  category,  author_name,  url, thumbnail_pic_s);
                //把所有的新闻放到newsList
                newsList.add(news);


//                System.out.println(title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    //通过第三方框架Gson来解析Json数据
    //1.在Gradle中导入Gson框架
    //2.根据Json解析中的格式，创建对应的JavaBean:NewsJsonBean
    //3.直接解析Json工具


    public  static  List<NewsJson.ResultBean.DataBean> parseJsonWithGson(String jsonStr){
        //创建出Gson工具`
        Gson gson=new Gson();
        //解析数据
        NewsJson newsJson =gson.fromJson(jsonStr,NewsJson.class);
        return newsJson.getResult().getData();
    }
}
