package com.lion.plugin.platform;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;


public class BaseHostActivity extends FragmentActivity {

    @Override
    public AssetManager getAssets() {
        return Platform.getInstance().getmAssetManager() == null ? super.getAssets() : Platform.getInstance().getmAssetManager();
    }

    @Override
    public Resources getResources() {
        return Platform.getInstance().getmResources() == null ? super.getResources() : Platform.getInstance().getmResources();
    }

    @Override
    public Theme getTheme() {
        return Platform.getInstance().getmTheme() == null ? super.getTheme() : Platform.getInstance().getmTheme();
    }

    @Override
    public ClassLoader getClassLoader() {
        return Platform.getInstance().getDexClassLoader() == null ? super.getClassLoader() : Platform.getInstance().getDexClassLoader();
    }

    public String getDexPath(){
        return Platform.getInstance().getmDexPath() ;
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return Platform.getInstance().getLayoutInflater() == null ? super.getLayoutInflater() : Platform.getInstance().getLayoutInflater();
    }
}
