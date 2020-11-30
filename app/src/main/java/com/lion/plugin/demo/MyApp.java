package com.lion.plugin.demo;

import android.app.Application;
import android.content.Context;

import com.lion.plugin.platform.Platform;

public class MyApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Platform.getInstance().attachBaseContext(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Platform.getInstance().initApplication();
    }
}
