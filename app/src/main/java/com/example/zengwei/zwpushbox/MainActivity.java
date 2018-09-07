package com.example.zengwei.zwpushbox;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.zengwei.zwpushbox.Base.MvpActivity;
import com.example.zengwei.zwpushbox.Model.Hitokoto;
import com.example.zengwei.zwpushbox.Page.anwo.Anwo;
import com.example.zengwei.zwpushbox.Util.GetRequestInterface;
import com.example.zengwei.zwpushbox.Util.PermissionsChecker;
import com.example.zengwei.zwpushbox.Util.SharedPreferencesHelper;
import com.example.zengwei.zwpushbox.Util.ZwBind;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {
    @ZwBind(R.id.animation_view)
    private LottieAnimationView lottieAnimationView;


    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE};
    List<String> mPermissionList = new ArrayList<>();
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mvpPresenter.attachView(this);
        super.injectViews();
    }

    @Override/**获取点击权限的回调**/
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (100 == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                showPermissionDialog();//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
            }else{
                //全部权限通过，可以进行下一步操作。。。
                mvpPresenter.init();
            }
        }

    }

    @Override
    public LottieAnimationView getLottieAnimationView() {
        return lottieAnimationView;
    }

    @Override
    public Activity getZActivity() {
        return this;
    }

    /**权限判断和申请**/
    private void initPermission() {
        /**清空没有通过的权限**/
        mPermissionList.clear();
        /**逐个判断你要的权限是否已经通过**/
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                /**添加还未授予的权限**/
                mPermissionList.add(permissions[i]);
            }
        }
        /**申请权限**/
        if (mPermissionList.size() > 0) {
            /**有权限没有通过，需要申请**/
            ActivityCompat.requestPermissions(this, permissions, 100);
        }else{
            /**说明权限都已经通过，可以做你想做的事情去**/
            mvpPresenter.init();
        }
    }

    /**
     * 不再提示权限时的展示对话框
     */
    AlertDialog mPermissionDialog;
    String mPackName = "com.example.zengwei.zwpushbox";

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(this).setMessage("已禁用权限，请手动授予")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelPermissionDialog();
                        Uri packageURI = Uri.parse("package:" + mPackName);
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭页面或者做其他操作
                        cancelPermissionDialog();
                        finish();
                    }
                })
            .create();
        }
        mPermissionDialog.show();
    }
    //关闭对话框
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**判断是否为6.0以上，如果是那么需要动态获取权限**/
        if(Build.VERSION.SDK_INT >= 23){
            initPermission();
        }
    }
}
