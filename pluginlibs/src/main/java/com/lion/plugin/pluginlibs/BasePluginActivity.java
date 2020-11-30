package com.lion.plugin.pluginlibs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

public class BasePluginActivity extends FragmentActivity implements IRemoteActivity {

    private static final String TAG = "Client-BaseActivity";

    /**
     * 等同于mProxyActivity，可以当作Context来使用，会根据需要来决定是否指向this<br/>
     * 可以当作this来使用
     */
    protected Activity that;
    protected String dexPath;

    public void setProxy(Activity proxyActivity, String dexPath) {
        that = proxyActivity;
        this.dexPath = dexPath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onNewIntent(Intent intent) {
//        that.onNewIntent(intent);
    }

    @Override
    public void onAttachedToWindow() {
//        that.onAttachedToWindow();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        that.onRequestPermissionsResult(requestCode,permissions,grantResults);

    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public View findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public void startActivity(Intent intent) {
        that.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        that.startActivityForResult(intent, requestCode);
    }

    @Override
    public Resources getResources() {
        return that.getResources();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    public void startPluginActivity(Context context, String activity){
        startPluginActivity(context,activity,null);
    }

    public void startPluginActivity(Context context,String activity,Bundle bundle){

        Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
        intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
        intent.putExtra(AppConstants.PROXY_ACTIVITY, activity);
        if (bundle != null){
            intent.putExtra(AppConstants.EXTRA_BUNDLE, bundle);
        }
        context.startActivity(intent);
    }

}
