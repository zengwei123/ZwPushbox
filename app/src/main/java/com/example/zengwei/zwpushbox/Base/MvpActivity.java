package com.example.zengwei.zwpushbox.Base;

import android.os.Bundle;

/**
 * Created by zengwei on 2018/8/22.
 */

public abstract class MvpActivity <P extends BasePresenter> extends BaseActivity  {
    protected P mvpPresenter;
    protected abstract P createPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter =null;
        }
    }
    public void init(){}
    public void setCcontrol(){}
}
