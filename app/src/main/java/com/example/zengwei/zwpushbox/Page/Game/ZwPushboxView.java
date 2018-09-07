package com.example.zengwei.zwpushbox.Page.Game;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zengwei.zwpushbox.CustomView.ZwDialog;
import com.example.zengwei.zwpushbox.Page.anwo.Anwo;
import com.example.zengwei.zwpushbox.R;
import com.example.zengwei.zwpushbox.Util.SharedPreferencesHelper;
import com.example.zengwei.zwpushbox.Util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by zengwei on 2018/8/28.
 */

public class ZwPushboxView extends View {
    private Paint paint;
    private int width,height,squareZ,padding,padding1;     //宽高格子宽度偏移
    private float ix = 0,iy = 0;    //记录点击的xy
    private int meX=0,meY=0;
    private int map[][]=new int[14][14];
    private int sum=0,guan=0;
    private Context context;
    private ArrayList<int[]> integers;
    private ArrayList<int[][]> bushu;
    private int bz=0;
    private TextView Level,Tsum;
    private Activity activity;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCepP3XOhvnCXiBEzJ37a74mNP1Qrwr/kdM4IPbNqO2DEKTTu6bYx2B7ousGMOY+ehjB0JOUhakBInCx7ecdu611LHdX32OLd9mzJsqyXWgr98wazIi716mNWpHEZSWJlew3kZhWhtZJvajD2BKfdVlXCyUPQCv2OfNsC0lKNHGmBXSIT2xV+Wpkxs+La0Q52y8MP/fSmiiZy1hxaijKIkGzK5GGYp1jVIS00qQOrcIx2ME7KUY0yd1ZQ1/Cfd2YfnP5v3vg7z37G6dK9vGV7gC4KNrqtgJeFerQyrCKk6WQufLfCBDkWPi0JDZveqQQgt/cO+BGz6WEuixiGHEkd3rAgMBAAECggEAeycDst92J5zvgeugIv9M88jXya37m38yZFhpo3PAFKNhzy7YBaIklufSX09+D7P4lV9eNqR81Oh89MrLG1gsKNUrGfl14zhzEz9EOSvY91tvvAuLCRme9RHEJdVc7k5nhnKWWh5z2npnvd9C1bDnRl8OcCkG7ntIPyFDenQ8nJfUD6q66JukB4KIK6QBnEuiOoEFsLLjfooqiWlrxVS6/qiSD7e1YTN34TmU1O0TQdVdG1vcAInfkgzQusQI+KRStwDLLLIbOuFR1gtnBc+BCLQDXtsIL41iVO/cEE3JEblt1+7AcLeWYqPABYI/noRCPyD1fyaMVHjPWu4ksSRpEQKBgQD+DM3c0r0vYuOR8d46t8Mxo6lmmHefmMjLtV1RH/x7wwdTRXtCnP1cixu5zM66J2HDX+uwCp7fvxR7luuHJ5FTNynSYxo+F02ATNxUdAPX5NKuxxL3pI6x7PWL2d0pHjR38yScidG5w4T+V0GktCmHZaAox7bddbz167qJ3LP/mQKBgQCf3LhhtAGWO2GRaKEUHlolPIMHXKD/kQo8W9TRy3qCGXMPOuEW2GmCd175DDwHB8R1/dUwBx/5xyEo/OHChXcpMvNFzDNaMIpWK9ATAI5SfgxyGEz8Z3DdyFDYoMSeLC/zqPVGKuBsBZyLADEpFAbCbfaxnqMVxJKS82LLt4jMIwKBgQDjBgQtEnGXV/JvKEnkBPU8dblbAmLbzweY6rDCXygcXNM9+bhFdc7/lRwotDoEpk657gzLGyKQZcvjCaHd/GDkkUYhSQhZdi9+uLCoE6iflE8leqabgIU3wL5IxF7bcK+IhVIxRQOS0oMVWlpB2fHGme0+pAHo817yED55Fi4P+QKBgFJPg2BLHsYLrSEBjm6dcizaC10vBHaSZ4LMhbe/3UQ/dk7RMjRhGXJjpDL3abD0/uMB31LrWDtznE9OZa+SqNg9s6Ssj4RQmenkhS5DiwROgnn8qScM28BV88qWfKY0ejwrMp7+VK9J9GBdqPJNrW1O0UaDN9VtcUrYpnXd+kGhAoGBAPW/1UP1zWx3gVbT/+j02iPXg1mHZDUhnR+whD0dunIORw5nvDl60sF03FEuIFKglx3nLbaDJvGoOsLqdbwJOp6fWtlWdpDwsTXSQB1SVdXdgUaNkZCFMCD0qmRqLxR3tkaLI5apMFnMXjiRFa39caXYD833z1arZK6Gd4S/BdcM";

    public ZwPushboxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        guan=(int)new SharedPreferencesHelper(context,"box").getSharedPreference("Level",1);
        integers=new ArrayList<>();
        bushu=new ArrayList<>();
        for(int i=0;i<14;i++){
            for(int j=0;j<14;j++){
                map[i][j]=PushboxMap.getMap(guan)[i][j];
                if(map[i][j]==4||map[i][j]==12){
                    integers.add(new int[]{i,j});
                }
            }
        }
        fan(new int[14][14]);
        sound();
        handler.sendMessage(handler.obtainMessage());
        this.activity=activity;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<14;i++){
            for(int j=0;j<14;j++){
                painting(canvas ,i,j,squareZ);
            }
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        squareZ=height>width?width/14-1:height/14-2;
        padding=(width-squareZ*14)/2;
        padding1=(height-squareZ*14)/2;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            ix=event.getX();
            iy=event.getY();
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            if(Math.abs(ix-event.getX())>Math.abs(iy-event.getY())){
                //左右
                if(ix>event.getX()){
                    //左
                    yidong(0);
                }else{
                    //右
                    yidong(1);
                }
            }else{
                //左右
                if(iy>event.getY()){
                    //上
                    yidong(2);
                }else{
                    //下
                    yidong(3);
                }
            }
        }
        return true;
    }

    /**
     * 移动
     */
    private void yidong(int i){
        switch (i){
            case 0:
                is(-1,0,-2,0);
                break;
            case 1:
                is(1,0,2,0);
                break;
            case 2:
                is(0,-1,0,-2);
                break;
            case 3:
                is(0,1,0,2);
                break;
        }
        invalidate();
    }

    private void is(int x1,int y1,int x2,int y2){
        if(map[meX+x1][meY+y1]!=PushboxMap.MAP_WALL){    //判断前面是否是墙
            if(map[meX+x1][meY+y1]==PushboxMap.MAP_WAY||map[meX+x1][meY+y1]==PushboxMap.MAP_GOAL){  //判断是否为路 或者 标记点
                if(map[meX][meY]!=PushboxMap.MAP_PERSON_GOAL){     //判断人现在是否是人终混合物
                    map[meX][meY]=PushboxMap.MAP_WAY;               //变成路
                }else{
                    map[meX][meY]=PushboxMap.MAP_GOAL;              //变成终点
                }
                if(map[meX+x1][meY+y1]==PushboxMap.MAP_WAY){                //前面是路
                    map[meX+x1][meY+y1]=PushboxMap.MAP_PERSON;             //现在的位置变成人
                }else{
                    map[meX+x1][meY+y1]=PushboxMap.MAP_PERSON_GOAL;        //否则变成人终混合物
                }
                sum++;
                fan(new int[14][14]);
            }else{
                if(map[meX+x1][meY+y1]==PushboxMap.MAP_BOX||map[meX+x1][meY+y1]==PushboxMap.MAP_BOX_GOAL){   //判断是否为箱子
                    if(map[meX+x2][meY+y2]==PushboxMap.MAP_WAY||map[meX+x2][meY+y2]==PushboxMap.MAP_GOAL){   //判断箱子前面是否为路 或者标记点
                        if(map[meX][meY]!=PushboxMap.MAP_PERSON_GOAL){     //判断人现在是否是人终混合物
                            map[meX][meY]=PushboxMap.MAP_WAY;               //变成路
                        }else{
                            map[meX][meY]=PushboxMap.MAP_GOAL;              //变成终点
                        }
                        if(map[meX+x1][meY+y1]==PushboxMap.MAP_BOX_GOAL){    //箱子为箱终混合物
                            map[meX+x1][meY+y1]=PushboxMap.MAP_PERSON_GOAL;   //变成人终混合物
                        }else{
                            map[meX+x1][meY+y1]=PushboxMap.MAP_PERSON;         //变成人
                        }
                        if(map[meX+x2][meY+y2]==PushboxMap.MAP_WAY){             //箱子前面是路
                            map[meX+x2][meY+y2]=PushboxMap.MAP_BOX;           //变成箱子
                        }else{                                                 //箱子前面是终点
                            map[meX+x2][meY+y2]=PushboxMap.MAP_BOX_GOAL;
                        }
                        sum++;
                        fan(new int[14][14]);
                    }
                }
            }
            meX+=x1;
            meY+=y1;
            PushboxMp3.playSound(context);
            handler.sendMessage(handler.obtainMessage());
            for(int iz[]:integers){
                if(map[iz[0]][iz[1]]==PushboxMap.MAP_BOX_GOAL){
                    bz++;
                }
            }
            if(bz==integers.size()){
                guan++;
                if(guan>(int)new SharedPreferencesHelper(context,"box").getSharedPreference("Level",1)){
                    new SharedPreferencesHelper(context,"box").put("Level",guan);
                }
              ZwDialog.getDialog(activity, "通关步数："+sum+" \ue91b", new ZwDialog.ZwDialogListener() {
                    @Override
                    public void yes() {
                        Reset();
                    }

                    @Override
                    public void no() {
                        guan--;
                        Reset();
                    }

                    @Override
                    public void fenxiang() {
                        saveCurrentImage();
                        File file = new File(context.getFilesDir().getPath()+"/disk.jpg");
                       Uri uri= FileProvider.getUriForFile(context,"com.example.zengwei.zwpushbox", file);
                        guan--;
                        Intent textIntent = new Intent(Intent.ACTION_SEND);
                        textIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        textIntent.setType("image/*");
                        textIntent.putExtra(Intent.EXTRA_STREAM, uri);
                        context.startActivity(Intent.createChooser(textIntent, "分享"));

                    }
                }).show();
            }
            bz=0;
        }
    }

    private void painting(Canvas canvas,int i,int j,int square){
        if(map[i][j]==0){
            paint.setColor(Color.parseColor("#2b2b2b"));
            if(i==0){
                canvas.drawRect(padding+1+square*i,padding1+2+square*j,padding+square*(i+1),padding1+square*(j+1),paint);
            }else {
                canvas.drawRect(padding+2+square*i,padding1+2+square*j,padding+square*(i+1),padding1+square*(j+1),paint);
            }
        }else if(map[i][j]==1){
        }else if(map[i][j]==2){
            canvas.drawBitmap(Util.loadImage(getContext(),square, R.mipmap.box),padding+2+square*i,padding1+2+square*j,paint);
        }else if(map[i][j]==3){
            meX=i;meY=j;
            canvas.drawBitmap(Util.loadImage(getContext(),square, R.mipmap.p),padding+2+square*i,padding1+2+square*j,paint);
        }else if(map[i][j]==4){
            paint.setColor(Color.parseColor("#8BACA1"));
            canvas.drawRect(padding+2+square*i,padding1+2+square*j,padding+square*(i+1),padding1+square*(j+1),paint);
        }else if(map[i][j]==11){
            meX=i;meY=j;
            paint.setColor(Color.parseColor("#8BACA1"));
            canvas.drawRect(padding+2+square*i,padding1+2+square*j,padding+square*(i+1),padding1+square*(j+1),paint);
            canvas.drawBitmap(Util.loadImage(getContext(),square, R.mipmap.p),padding+2+square*i,padding1+2+square*j,paint);
        }else if(map[i][j]==12){
            paint.setColor(Color.parseColor("#8BACA1"));
            canvas.drawRect(padding+2+square*i,padding1+2+square*j,padding+square*(i+1),padding1+square*(j+1),paint);
            canvas.drawBitmap(Util.loadImage(getContext(),square, R.mipmap.box1),padding+2+square*i,padding1+2+square*j,paint);
        }
    }
    /**
     * 下一关
     */
    public void next(){
        /**
         * 小于最大的通过数直接过
         */
        int i=(int)new SharedPreferencesHelper(context,"box").getSharedPreference("Level",1);
        if(guan+1<=i){
                if(guan+1<=PushboxMap.sum){
                    guan++;
                    Reset();
                    invalidate();
                }
        } else{
            /**
             * true 下一关按钮   false回退按钮
             */
            Snackbar sl=Snackbar.make(activity.findViewById(android.R.id.content),"是否跳过此关？",Snackbar.LENGTH_LONG)
                    .setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            guan++;
                            new SharedPreferencesHelper(context,"box").put("Level",guan);
                            Reset();
                            Intent intent=new Intent(context, Anwo.class);
                            context.startActivity(intent);
                        }
                    });
            sl.show();
        }
    }

    /**
     * 刷新
     */
    public void Reset(){
        integers.clear();bushu.clear();
        for(int i=0;i<14;i++){
            for(int j=0;j<14;j++){
                map[i][j]=PushboxMap.getMap(guan)[i][j];
                if(map[i][j]==4||map[i][j]==12){
                    integers.add(new int[]{i,j});
                }
            }
        }
        fan(new int[14][14]);
        bz=0;
        sum=0;
        invalidate();
        handler.sendMessage(handler.obtainMessage());
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(this,"alpha",0,1f);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

    }

    public void Left(){
        if(guan>1){
            guan--;
            Reset();
        }else{
            Toast.makeText(context, "当前为第一关", Toast.LENGTH_SHORT).show();
        }
    }

    public TextView getLevel() {
        return Level;
    }

    public void setLevel(TextView level) {
        Level = level;
    }

    public TextView getTsum() {
        return Tsum;
    }

    public void setTsum(TextView tsum) {
        Tsum = tsum;
    }
    public void sound(){
        PushboxMp3.playSound1(context);
    }
    public void returnz(){
        if(bushu.size()-1>0){
            for(int i=0;i<14;i++){
                for(int j=0;j<14;j++){
                    map[i][j]=bushu.get(bushu.size()-2)[i][j];

                }
            }
            bushu.remove(bushu.size()-1);
            invalidate();
        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            getLevel().setText("第"+guan+"关");
            getTsum().setText(sum+"步");
        }
    };

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void fan(int[][] zmaps){
        for(int i=0;i<14;i++){
            for(int j=0;j<14;j++){
                zmaps[i][j]=map[i][j];
            }
        }
        bushu.add(zmaps);
    }
    public void tiao(int i){
        guan=i;
        Reset();
    }
    //这种方法状态栏是空白，显示不了状态栏的信息
    private Bitmap saveCurrentImage() {
        //获取当前屏幕的大小
        int width = activity.getWindow().getDecorView().getRootView().getWidth();
        int height = activity.getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
        //找到当前页面的跟布局
        View view =  activity.getWindow().getDecorView().getRootView();
        //设置缓存
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        File file=new File(context.getFilesDir().getPath()+"/disk.jpg");
        file.delete();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            temBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temBitmap;
    }
}
