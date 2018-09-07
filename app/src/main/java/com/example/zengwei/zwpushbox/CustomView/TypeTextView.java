package com.example.zengwei.zwpushbox.CustomView;


import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.AttributeSet;

import com.example.zengwei.zwpushbox.Util.Util;


/**
 * Created by zengwei on 2018/7/7.
 * 重写用来设置字体图库
 */

public class TypeTextView extends android.support.v7.widget.AppCompatTextView {
    private String s;
    public TypeTextView(Context context) {
        super(context);
        setTypeface(Util.getTypeFace(context));
        setTextColor(Color.parseColor("#2b2b2b"));
        s=getText().toString();
        setText(Html.fromHtml(s));
    }
    public TypeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Util.getTypeFace(context));
        setTextColor(Color.parseColor("#2b2b2b"));s=getText().toString();
        setText(Html.fromHtml(s));
    }
    public TypeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Util.getTypeFace(context));
        setTextColor(Color.parseColor("#2b2b2b"));
        s=getText().toString();
        setText(Html.fromHtml(s));
    }
}
