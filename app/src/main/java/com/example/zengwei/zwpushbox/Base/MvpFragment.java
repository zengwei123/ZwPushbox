package com.example.zengwei.zwpushbox.Base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by zengwei on 2018/8/23.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment  {
    protected P mvpPresenter;
    protected abstract P createPresenter();
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mvpPresenter=createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
