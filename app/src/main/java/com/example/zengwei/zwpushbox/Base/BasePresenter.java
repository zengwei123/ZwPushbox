package com.example.zengwei.zwpushbox.Base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by zengwei on 2018/8/22.
 */

public class BasePresenter<V> {
    public V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }


    public void detachView() {
        this.mvpView = null;
    }
    public void init(){}
    public void H(Handler handler, String key, String result,int i){
        Message message=handler.obtainMessage();
        message.what=i;
        Bundle bundle=new Bundle();bundle.putString(key,result);
        message.setData(bundle);
        handler.sendMessage(message);
    }
}