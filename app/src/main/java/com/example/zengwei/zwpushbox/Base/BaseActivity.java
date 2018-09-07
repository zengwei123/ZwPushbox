package com.example.zengwei.zwpushbox.Base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import com.example.zengwei.zwpushbox.Util.ZwBind;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengwei on 2018/8/22.
 */

public class BaseActivity extends AppCompatActivity {
    public static List<BaseActivity> baseActivities; //关闭activity
    private static Intent intent;   //跳转Activity方法
    /**
     * 添加Activity
     * @param baseActivity
     */
    public void addActivity(BaseActivity baseActivity){
        if (baseActivities==null){
            baseActivities=new ArrayList<>();
        }
        baseActivities.add(baseActivity);
    }

    /**
     * 关闭Activity
     */
    public void finishAll(){
        for (BaseActivity activity:baseActivities){
            if (activity!=null){
                activity.finish();
            }
        }
    }

    /**
     * 跳转activity 方法
     * @param context
     * @param iclass
     */
    public void JumpActivity(Context context, Class iclass){
        intent=new Intent(context,iclass);
        startActivity(intent);
    }

    protected void injectViews(){
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ZwBind.class)) {
                ZwBind inject = field.getAnnotation(ZwBind.class);
                int id = inject.value();
                if (id > 0) {
                    field.setAccessible(true);
                    try {
                        field.set(this, this.findViewById(id));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
