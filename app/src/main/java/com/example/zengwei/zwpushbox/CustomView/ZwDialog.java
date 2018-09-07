package com.example.zengwei.zwpushbox.CustomView;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.example.zengwei.zwpushbox.R;

/**
 * Created by zengwei on 2018/9/2.
 */

public class ZwDialog {
    private static TextView textView3,quxiao,next;
    public static AlertDialog getDialog(Activity activity, String s, final ZwDialogListener zwDialogListener){
        final AlertDialog builder = new AlertDialog.Builder(activity).create();
        builder.setCancelable(false);
        View view=activity.getLayoutInflater().inflate(R.layout.dialog,null);
        textView3=view.findViewById(R.id.textView3);
        quxiao=view.findViewById(R.id.quxiao);
        next=view.findViewById(R.id.next);
        textView3.setText(s);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();

                zwDialogListener.fenxiang();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
                zwDialogListener.no();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
                zwDialogListener.yes();
            }
        });

        builder.setView(view);
        return  builder;
    }
public  interface ZwDialogListener{
        void yes();
        void no();
        void fenxiang();
    }
}
