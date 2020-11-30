package com.lion.plugin.platform;

import android.app.Activity;
import android.app.Application;

import com.lion.plugin.pluginlibs.IPluginCallback;

public class GMSDK {

    public static void initPlugin(Application application){
        Platform.getInstance().initPlugin(application);
    }

    public static void initPlatform(Activity activity, IPluginCallback iPluginCallback){
        Platform.getInstance().setMainActivity(activity,iPluginCallback);
    }

    public static void gmsdkLogin(){
        Platform.getInstance().login();
    }

}
