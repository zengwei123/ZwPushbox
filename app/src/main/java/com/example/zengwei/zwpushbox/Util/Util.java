package com.example.zengwei.zwpushbox.Util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by zengwei on 2018/8/23.
 */

public class Util {
    /**
     * 字体图库设置
     * @param context
     * @return
     */
    public static Typeface getTypeFace(Context context){
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"font/icomoon.ttf");
        return typeface;
    }

    /**
     * 获取图片并设置大小123
     * @param context
     * @param width
     * @param image
     * @return
     */
    public static Bitmap loadImage(Context context, int width, int image){
        Resources res =context.getResources();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,image), width, width, true);
    }

    /**
     * 屏幕最小尺寸
     * @param activity
     * @return
     */
    public static int getScreenManager(Activity activity){
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width2 = outMetrics.widthPixels;
        int height2 = outMetrics.heightPixels;
        return width2>height2?height2:width2;
    }
}
