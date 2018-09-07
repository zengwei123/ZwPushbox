package com.example.zengwei.zwpushbox.Base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.example.zengwei.zwpushbox.Util.ZwBind;

import java.lang.reflect.Field;

/**
 * Created by zengwei on 2018/8/23.
 */

public class BaseFragment extends Fragment {
    public Activity activity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity=getActivity();
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
                        field.set(this, activity.findViewById(id));
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
