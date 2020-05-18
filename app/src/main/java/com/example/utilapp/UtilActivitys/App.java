package com.example.utilapp.UtilActivitys;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.utilapp.Util.AbstractContextUtil;
import com.example.utilapp.Util.ToastUtil;

public class App extends Application {
    private static final String TAG = "UtilApp";
    @Override
    public void onCreate() {
        super.onCreate();
        Context context=getApplicationContext();
        ToastUtil.onApplicationInit(context);
        AbstractContextUtil.onApplicationInit(context);
    }
}
