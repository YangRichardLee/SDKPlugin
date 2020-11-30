package com.lion.plugin.sdkplugin;

import android.os.Bundle;

import com.lion.plugin.pluginlibs.BasePluginActivity;


public class SecondActivity extends BasePluginActivity {

    private static final String TAG = "Client-SecondActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
}