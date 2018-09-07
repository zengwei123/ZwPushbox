package com.example.zengwei.zwpushbox.Page.Game;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zengwei.zwpushbox.R;

/**
 * Created by zengwei on 2018/8/28.
 */

public class PushboxRecyclerAdatper extends RecyclerView.Adapter {
    private Activity activity;
    private int gameItems;
    private PushboxRecyclerListener pushboxRecyclerListener;
    public PushboxRecyclerAdatper(Activity activity, int gameItems, PushboxRecyclerListener pushboxRecyclerListener){
        this.activity=activity;
        this.gameItems=gameItems;
        this.pushboxRecyclerListener=pushboxRecyclerListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.game_a_pushbox_item,parent,false);
        return new PushboxHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final PushboxHolder pushboxHolder= (PushboxHolder) holder;
        pushboxHolder.textView.setText((position+1)+"");
        pushboxHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Animation.alpha(pushboxHolder.textView);
                pushboxRecyclerListener.but(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameItems;
    }

    class PushboxHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public PushboxHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
