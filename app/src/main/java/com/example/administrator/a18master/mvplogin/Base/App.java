package com.example.administrator.a18master.mvplogin.Base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

public class App extends Application {
    private static Context context;
    public static boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取Context
        context = getApplicationContext();
        LeakCanary.install(this);
    }

    //返回全局Context
    public static Context getAppContext() {
        return context;
    }
}
