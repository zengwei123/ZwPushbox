package com.example.zengwei.zwpushbox.Util;

import com.example.zengwei.zwpushbox.Model.Hitokoto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by zengwei on 2018/8/30.
 */
public interface GetRequestInterface {
    @GET()
    Call<Hitokoto> getHitokoto(@Url String url);
    @GET()
    Call<String> getUrl(@Url String url);
    @GET("{message}")
    Call<String> getMessage(@Path("message") String message);
}
