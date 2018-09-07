package com.example.zengwei.zwpushbox.Page.Index;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zengwei.zwpushbox.Base.MvpActivity;
import com.example.zengwei.zwpushbox.Page.Game.PushboxRecyclerAdatper;
import com.example.zengwei.zwpushbox.Page.Game.ZwPushboxView;
import com.example.zengwei.zwpushbox.R;
import com.example.zengwei.zwpushbox.Util.ZwBind;

/**
 * Created by zengwei on 2018/8/31.
 */
public class IndexActivity extends MvpActivity<IndexPresenter> implements IndexView{
    @ZwBind(R.id.next)
    private TextView next;
    @ZwBind(R.id.Reset)
    private TextView Reset;
    @ZwBind(R.id.left)
    private TextView Left;
    @ZwBind(R.id.right)
    private TextView Right;
    @ZwBind(R.id.sum)
    private TextView sum;
    @ZwBind(R.id.sound)
    private TextView sound;
    @ZwBind(R.id.item)
    private TextView item;
    @ZwBind(R.id.Level)
    private TextView Level;
    @ZwBind(R.id.ZwPushboxView)
    private ZwPushboxView zwPushboxView;
    @ZwBind(R.id.PushboxRecycler)
    private RecyclerView recyclerView;
    @ZwBind(R.id.RelativeLayout)
    private android.widget.RelativeLayout RelativeLayout;
    private boolean aBoolean=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        mvpPresenter.attachView(this);
        super.injectViews();
        mvpPresenter.init();
    }

    @Override
    public void onBackPressed() {
        if(RelativeLayout.getVisibility()== View.VISIBLE){
            RelativeLayout.setVisibility(View.GONE);
        }else{
            if(aBoolean){
                finish();
            }else{
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                aBoolean=true;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            aBoolean=false;
                            //执行需要执行的任务
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }

    @Override
    protected IndexPresenter createPresenter() {
        return new IndexPresenter();
    }

    @Override
    public Activity getZActivity() {
        return this;
    }

    @Override
    public TextView getReset() {
        return Reset;
    }

    @Override
    public TextView getNext() {
        return next;
    }

    @Override
    public TextView getLeft() {
        return Left;
    }

    @Override
    public TextView getRight() {
        return Right;
    }

    @Override
    public TextView getSound() {
        return sound;
    }

    @Override
    public TextView getItem() {
        return item;
    }

    @Override
    public TextView getSum() {
        return sum;
    }

    @Override
    public TextView getLevel() {
        return Level;
    }

    @Override
    public ZwPushboxView getZwPushboxView() {
        return zwPushboxView;
    }

    @Override
    public void setPushboxRecycler(PushboxRecyclerAdatper pushboxRecyclerAdatper) {
        recyclerView.setLayoutManager(getLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(pushboxRecyclerAdatper);
    }

    @Override
    public RelativeLayout getRelativeLayout() {
        return RelativeLayout;
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        final GridLayoutManager manager = new GridLayoutManager(context, 4);//网格布局，4列
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {//动态修改列数
            @Override
            public int getSpanSize(int position) {
                return 1;//当position=0时，Item占用一整行；
            }
        });
        return manager;
    }
}
