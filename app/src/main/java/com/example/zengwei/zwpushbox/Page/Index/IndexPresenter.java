package com.example.zengwei.zwpushbox.Page.Index;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.zengwei.zwpushbox.Base.BasePresenter;
import com.example.zengwei.zwpushbox.MainView;
import com.example.zengwei.zwpushbox.Page.Game.PushboxMap;
import com.example.zengwei.zwpushbox.Page.Game.PushboxMp3;
import com.example.zengwei.zwpushbox.Page.Game.PushboxRecyclerAdatper;
import com.example.zengwei.zwpushbox.Page.Game.PushboxRecyclerListener;
import com.example.zengwei.zwpushbox.R;
import com.example.zengwei.zwpushbox.Util.SharedPreferencesHelper;

/**
 * Created by zengwei on 2018/8/31.
 */

public class IndexPresenter extends BasePresenter<IndexView> implements View.OnClickListener {
    @Override
    public void init() {
        mvpView.getNext().setOnClickListener(this);
        mvpView.getReset().setOnClickListener(this);
        mvpView.getLeft().setOnClickListener(this);
        mvpView.getRight().setOnClickListener(this);
        mvpView.getSound().setOnClickListener(this);
        mvpView.getItem().setOnClickListener(this);
        mvpView.getZwPushboxView().setLevel(mvpView.getLevel());
        mvpView.getZwPushboxView().setTsum(mvpView.getSum());
        mvpView.getZwPushboxView().setActivity(mvpView.getZActivity());
        if((int)new SharedPreferencesHelper(mvpView.getZActivity(),"box").getSharedPreference("mp3",0)==1){
            mvpView.getSound().setText("\ue050");
        }else{
            mvpView.getSound().setText("\ue04f");
        }
        mvpView.setPushboxRecycler(new PushboxRecyclerAdatper(mvpView.getZActivity(), PushboxMap.sum,new PushboxRecyclerListener() {
            @Override
            public void but(int position) {
                if(position+1>(int)new SharedPreferencesHelper(mvpView.getZActivity(),"box").getSharedPreference("Level",1)){
                    Toast.makeText(mvpView.getZActivity(), "此关卡未解锁", Toast.LENGTH_SHORT).show();
                }else{
                    mvpView.getRelativeLayout().setVisibility(View.GONE);
                    mvpView.getZwPushboxView().tiao(++position);
                }
            }
        }));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                mvpView.getZwPushboxView().next();
                break;
            case R.id.Reset:
                mvpView.getZwPushboxView().Reset();
                break;
            case R.id.left:
                mvpView.getZwPushboxView().Left();
                break;
            case R.id.right:
                mvpView.getZwPushboxView().returnz();
                break;
            case R.id.sound:
                if((int)new SharedPreferencesHelper(mvpView.getZActivity(),"box").getSharedPreference("mp3",0)==1){
                    new SharedPreferencesHelper(mvpView.getZActivity(),"box").put("mp3",0);
                    mvpView.getSound().setText("\ue04f");
                    PushboxMp3.stop(true,mvpView.getZActivity());
                }else{
                    new SharedPreferencesHelper(mvpView.getZActivity(),"box").put("mp3",1);
                    Log.d("zengwei123","执行");
                    mvpView.getSound().setText("\ue050");
                    mvpView.getZwPushboxView().sound();
                }
                break;
            case R.id.item:
                mvpView.getRelativeLayout().setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(  mvpView.getRelativeLayout(),"alpha",0,1f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                break;
        }
    }
}
