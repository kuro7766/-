package com.example.utilapp.Util;

import android.app.Application;
import android.content.Context;

public class AbstractContextUtil extends Application {
    protected static Context context;
    public static void onApplicationInit(Context context){
        AbstractContextUtil.context=context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if(context==null)
            context=getApplicationContext();
    }
}
