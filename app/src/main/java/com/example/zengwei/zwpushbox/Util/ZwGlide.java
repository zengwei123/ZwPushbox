package com.example.zengwei.zwpushbox.Util;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.example.zengwei.zwpushbox.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by zengwei on 2018/8/31.
 */

public class ZwGlide extends Thread {
    private Context context;
    private String Url;
    public ZwGlide(Context context, String Url){
        this.context=context;
        this.Url=Url;
    }
    @Override
    public void run() {
        try {
            File file=Glide.with(context).load(Url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
            FileInputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
            FileOutputStream outputStream = context.openFileOutput("disk.jpg", context.MODE_PRIVATE);
            int tempbyte;
            while ((tempbyte = bufferedInputStream.read()) != -1) {
                outputStream.write(tempbyte);
            }
            outputStream.flush();
            outputStream.close();
            bufferedInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private BitmapDrawable diskImage(){
        File file=new File(context.getFilesDir().getPath()+"/disk.jpg");
        FileInputStream fileInputStream;
        Bitmap bitmap;
        BitmapDrawable bd;
        try {
            if (file.exists()) {
                /**
                 * 如果文件存在，那么说明不是第一次进入
                 */
                fileInputStream=new FileInputStream(file);
                bitmap= BitmapFactory.decodeStream(fileInputStream);
                bd=new  BitmapDrawable(context.getResources(),bitmap);
                return bd;
            }
        } catch (Exception e) {}
        /**
         * 如果文件不存在，那么是第一次进入
         */
        bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.loading);
        bd=new BitmapDrawable(context.getResources(),bitmap);
        return bd;
    }
}
