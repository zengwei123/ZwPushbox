package com.example.zengwei.zwpushbox.Page.Game;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.zengwei.zwpushbox.R;
import com.example.zengwei.zwpushbox.Util.SharedPreferencesHelper;

/**
 * Created by zengwei on 2018/9/1.
 */

public class PushboxMp3 {
    private static MediaPlayer mediaplayer = null;
    private static MediaPlayer mediaplayer1 = null;
    public static void playSound(Context context) {
        if(mediaplayer==null){
            mediaplayer = MediaPlayer.create(context, R.raw.a);
        }
        if((int)new SharedPreferencesHelper(context,"box").getSharedPreference("mp3",0)==1){
            mediaplayer.start();
        }
    }
    public static void playSound1(final Context context) {
            mediaplayer1 = MediaPlayer.create(context, R.raw.china);
            mediaplayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    playSound1(context);
                }
            });
        if((int)new SharedPreferencesHelper(context,"box").getSharedPreference("mp3",0)==1) {
            mediaplayer1.start();
        }
    }
    public static void stop(boolean b,Context context){
        if(b){
            if(mediaplayer1!=null){
                mediaplayer1.stop();
            }
        }else{
            playSound1(context);
        }
    }
}