package com.example.zengwei.zwpushbox.Util;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zengwei on 2018/8/31.
 */

public class ZwRetrofit {
    private static ZwRetrofit zwRetrofit=null;
    private static Converter.Factory factory=null;
    private ZwRetrofit(){}
    public static ZwRetrofit getZwRetrofit(){
        if(zwRetrofit==null){
            zwRetrofit=new ZwRetrofit();
        }
        return zwRetrofit;
    }
    public GetRequestInterface getRequestInterface(String url,boolean b){
        if(b){
            factory= GsonConverterFactory.create();
        }else{
            factory= ScalarsConverterFactory.create();
        }
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .addConverterFactory(factory)
                .baseUrl(url)
                .build();
        GetRequestInterface request = retrofit.create(GetRequestInterface.class);
        return request;
    }
}
