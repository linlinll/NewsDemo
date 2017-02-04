package com.zhuoxin.news.retorfit;

import com.zhuoxin.news.utils.JsonImage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nh on 2017/1/13.
 */

public interface ImageApi {
    @GET("api/data/福利/10/1")
    Call<JsonImage> getResponse();
}
