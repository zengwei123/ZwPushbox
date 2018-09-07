package com.example.zengwei.zwpushbox.Util;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zengwei on 2018/8/31.
 */

public abstract class ZwRetrofitCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        ok(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
    public abstract void ok(Object s);
}
