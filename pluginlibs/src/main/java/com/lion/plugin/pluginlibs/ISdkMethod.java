package com.lion.plugin.pluginlibs;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.view.LayoutInflater;

import java.util.Map;

public interface ISdkMethod {
    public void setApplication(Application application);
    public void setMainActivity(Activity activity,IPluginCallback pluginCallback);
    public void setDexPath(String dexPath);
    public void login();
    public void showLoginView();
    public void logout();
    public void submitRoleInfo(String roleInfo);
    public void pay(Map<String,String> payInfo);
    public void onResume();
    public void showFloatView();
    public void setResources(Resources resources);
    public void setLayoutInflater(LayoutInflater layoutInflater);
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
}
