package com.example.zengwei.zwpushbox.Page.anwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zengwei.zwpushbox.Base.MvpActivity;
import com.example.zengwei.zwpushbox.R;
import com.example.zengwei.zwpushbox.Util.ZwBind;
import com.sixth.adwoad.ErrorCode;
import com.sixth.adwoad.InterstitialAd;
import com.sixth.adwoad.InterstitialAdListener;

/**
 * Created by zengwei on 2018/9/6.
 */

public class Anwo extends AppCompatActivity implements InterstitialAdListener{
    public static byte FS_DESIRE_AD_FORM = 0;
    private InterstitialAd ad;
    private   int sb=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anwo);
        ad = new InterstitialAd(this,"0207284e618441a3baf5a5644b507f32",false,this);
        // 设置全屏格式
        ad.setDesireAdForm(FS_DESIRE_AD_FORM);
        // 设置请求广告类型 可选。
        ad.setDesireAdType((byte) 0);
        // 开始请求全屏广告
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
        finish();
    }

    @Override
    public void OnShow() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ad != null) {
            ad.dismiss();
            ad = null;
        }
    }
    @Override
    public void onBackPressed() {

    }
}
