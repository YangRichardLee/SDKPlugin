package com.lion.plugin.sdkplugin.sdk.platform;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.lion.plugin.pluginlibs.AppStatus;
import com.lion.plugin.pluginlibs.IPluginCallback;
import com.lion.plugin.pluginlibs.ISdkMethod;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.callback.CallBackManager;
import com.lion.plugin.sdkplugin.sdk.permission.PermissionUtils;
import com.lion.plugin.sdkplugin.sdk.permission.request.IRequestPermissions;
import com.lion.plugin.sdkplugin.sdk.permission.request.RequestPermissions;
import com.lion.plugin.sdkplugin.sdk.permission.requestresult.IRequestPermissionsResult;
import com.lion.plugin.sdkplugin.sdk.permission.requestresult.RequestPermissionsResultSetApp;
import com.lion.plugin.sdkplugin.sdk.precenter.UserPresenter;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;
import com.lion.plugin.sdkplugin.sdk.widget.floatwindow.FloatWindow;

import java.util.Map;

public class PluginPlatform implements ISdkMethod {

    private Application mApplication;
    private static Activity mActivity;
    private String mDexPath;

    private static PluginPlatform instance;

    private static Resources mResources;
    private static LayoutInflater mLayoutInflater;

    private IRequestPermissions requestPermissions = RequestPermissions.getInstance();//动态权限请求
    private IRequestPermissionsResult requestPermissionsResult = RequestPermissionsResultSetApp.getInstance();//动态权限请求结果处理

    public Application getApplication() {
        return mApplication;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public String getDexPath() {
        return mDexPath;
    }

    /**
     * 获取插件路径
     * @param mDexPath
     */
    @Override
    public void setDexPath(String mDexPath) {
        this.mDexPath = mDexPath;
    }

    //请求权限
    public boolean requestPermissions() {
        //需要请求的权限
        String[] permissions;
        permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
        //开始请求权限
        return requestPermissions.requestPermissions(
                mActivity,
                permissions,
                PermissionUtils.ResultCode);
    }

    public static PluginPlatform getInstance() {
        if (instance == null){
            synchronized (PluginPlatform.class){
                if (instance == null){
                    instance = new PluginPlatform();
                }
            }
        }
        return instance;
    }

    /**
     * 设置Application
     * @param application
     */
    public void setApplication(Application application){
        Log.d("Platform", "setApplication");
        mApplication = application;
    }

    /**
     * 初始化SDK 获取Activity
     * @param activity
     */
    @Override
    public void setMainActivity(Activity activity, IPluginCallback iPluginCallback) {
        mActivity = activity;
        if (iPluginCallback != null){
            CallBackManager.getInstance().setCallback(iPluginCallback);
        }
        if (!requestPermissions()){
            return;
        }
        CallBackManager.makeCallBack(AppStatus.INIT_SUCCESS);
        Config.setGameId(activity,"773");
    }

    /**
     * 登录
     */
    public void login(){
        Log.d("Platform", "login");
        UserPresenter.startLogin(mActivity);
    }

    @Override
    public void showLoginView() {
        UserPresenter.showLoginActivity(mActivity);
    }

    @Override
    public void logout() {

    }

    @Override
    public void submitRoleInfo(String roleInfo) {

    }

    @Override
    public void pay(Map<String, String> payInfo) {

    }

    @Override
    public void onResume() {
        Log.e("Platform","onResume");
    }

    @Override
    public void showFloatView() {
        FloatWindow.getInstance(getActivity()).show();
    }

    @Override
    public void setResources(Resources resources) {
        mResources = resources;
    }

    @Override
    public void setLayoutInflater(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public LayoutInflater getLayoutInflater() {
        ULogUtil.e("getLayoutInflater", String.valueOf(mLayoutInflater == null));
        return mLayoutInflater;
    }

    public Resources getResources(){
        ULogUtil.e("getResources", String.valueOf(mResources == null));
        return mResources;
    }

    //用户授权操作结果（可能授权了，也可能未授权）
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PermissionUtils.ResultCode){
            //用户给APP授权的结果
            //判断grantResults是否已全部授权，如果是，执行相应操作，如果否，提醒开启权限
            if (requestPermissionsResult.doRequestPermissionsResult(mActivity, permissions, grantResults)) {
                //请求的权限全部授权成功，此处可以做自己想做的事了
                //输出授权结果
                setMainActivity(mActivity,null);
            } else {
                //输出授权结果
                CallBackManager.makeCallBack(AppStatus.INIT_FALIED);
                Toast.makeText(mActivity, "请给APP授权，否则功能无法正常使用！", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
