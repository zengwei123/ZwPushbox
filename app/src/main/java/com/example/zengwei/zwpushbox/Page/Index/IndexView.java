package com.example.zengwei.zwpushbox.Page.Index;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zengwei.zwpushbox.Page.Game.PushboxRecyclerAdatper;
import com.example.zengwei.zwpushbox.Page.Game.ZwPushboxView;

/**
 * Created by zengwei on 2018/8/31.
 */

public interface IndexView {
    Activity getZActivity();
    TextView getReset();
    TextView getNext();
    TextView getLeft();
    TextView getRight();
    TextView getSound();
    TextView getItem();
    TextView getSum();
    TextView getLevel();
    ZwPushboxView getZwPushboxView();
    void setPushboxRecycler(PushboxRecyclerAdatper pushboxRecyclerAdatper);
    RelativeLayout getRelativeLayout();
}
