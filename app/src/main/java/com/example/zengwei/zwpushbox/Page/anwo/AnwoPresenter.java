package com.example.zengwei.zwpushbox.Page.anwo;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.zengwei.zwpushbox.Base.BasePresenter;
import com.example.zengwei.zwpushbox.R;
import com.example.zengwei.zwpushbox.Util.Util;
import com.sixth.adwoad.ErrorCode;
import com.sixth.adwoad.InterstitialAd;
import com.sixth.adwoad.InterstitialAdListener;
import com.sixth.adwoad.NativeAdListener;
import com.sixth.adwoad.NativeAdView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zengwei on 2018/9/6.
 */

public class AnwoPresenter extends BasePresenter<AnwoView> implements InterstitialAdListener{
    private InterstitialAd ad;
    public static byte FS_DESIRE_AD_FORM = 0;
    @Override
    public void init() {
        ad=new InterstitialAd(mvpView.getActivityz(), "0207284e618441a3baf5a5644b507f32", true, this);
        ad.setDesireAdForm(FS_DESIRE_AD_FORM);
        ad.prepareAd();
    }

    @Override
    public void onReceiveAd() {

    }

    @Override
    public void onLoadAdComplete() {
        ad.displayAd();
    }

    @Override
    public void onFailedToReceiveAd(ErrorCode errorCode) {

    }

    @Override
    public void onAdDismiss() {

    }

    @Override
    public void OnShow() {

    }
}
