package com.example.zengwei.zwpushbox;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by zengwei on 2018/8/30.
 */

public interface MainView {
    LottieAnimationView getLottieAnimationView();
    Activity getZActivity();
}
