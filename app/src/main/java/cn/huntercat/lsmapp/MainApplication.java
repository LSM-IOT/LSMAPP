package cn.huntercat.lsmapp;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return MainApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化应用上下文
        MainApplication.context = getApplicationContext();
    }
}
