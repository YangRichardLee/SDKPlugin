package com.lion.plugin.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.lion.plugin.platform.DLUtils;
import com.lion.plugin.platform.Platform;
import com.lion.plugin.pluginlibs.AppStatus;
import com.lion.plugin.pluginlibs.IPluginCallback;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startInit();
    }


    public void startLogin(View view) {
        Platform.getInstance().login();
    }

    public void showLoginView(View view) {
        Platform.getInstance().showLoginView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Platform.getInstance().onResume();
    }

    public void startInit(View view) {
        Platform.getInstance().setMainActivity(MainActivity.this, new IPluginCallback() {
            @Override
            public void onCallBack(Message mMessage) {
                switch (mMessage.what) {
                    case AppStatus.INIT_SUCCESS:// 初始化sdk成功回调
                        String s = (String) mMessage.obj;
                        Toast.makeText(MainActivity.this, "初始化成功", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    public void startInit() {
        Platform.getInstance().setMainActivity(MainActivity.this, new IPluginCallback() {
            @Override
            public void onCallBack(Message mMessage) {
                switch (mMessage.what) {
                    case AppStatus.INIT_SUCCESS:// 初始化sdk成功回调
                        String s = (String) mMessage.obj;
                        Toast.makeText(MainActivity.this, "初始化成功", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Platform.getInstance().onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    public void showFloatView(View view) {
        Platform.getInstance().showFloatView();
    }
}