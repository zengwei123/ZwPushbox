package com.example.zengwei.zwpushbox;


import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.zengwei.zwpushbox.Base.BasePresenter;
import com.example.zengwei.zwpushbox.Page.Index.IndexActivity;


/**
 * Created by zengwei on 2018/8/30.
 */

public class MainPresenter extends BasePresenter<MainView> {
    @Override
    public void init() {
        mvpView.getLottieAnimationView().addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent=new Intent(mvpView.getZActivity(), IndexActivity.class);
                mvpView.getZActivity().startActivity(intent);
                mvpView.getZActivity().overridePendingTransition(R.anim.activity_donghua_alpha_2,R.anim.activity_donghua_alpha_1);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mvpView.getLottieAnimationView().playAnimation();
    }

}
