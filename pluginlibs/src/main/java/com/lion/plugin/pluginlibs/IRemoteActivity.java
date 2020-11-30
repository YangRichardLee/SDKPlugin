package com.lion.plugin.pluginlibs;

/**
 * Created by jianqiang on 17/1/11.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface IRemoteActivity {
    public void onStart();
    public void onRestart();
    public void onAttachedToWindow();
    public void onNewIntent(Intent intent);
    public void onActivityResult(int requestCode, int resultCode, Intent data);
    public void onRequestPermissionsResult(int requestCode,String[] permissions,  int[] grantResults);
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onCreate(Bundle savedInstanceState);
    public void setProxy(Activity proxyActivity, String dexPath);
}