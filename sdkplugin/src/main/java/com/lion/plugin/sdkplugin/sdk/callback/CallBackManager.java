package com.lion.plugin.sdkplugin.sdk.callback;

import android.os.Message;

import com.lion.plugin.pluginlibs.IPluginCallback;

public class CallBackManager {

    private static volatile CallBackManager mInstance;

    private CallBackManager() {
    }

    public static CallBackManager getInstance() {
        if (mInstance == null) {
            synchronized (CallBackManager.class) {
                if (mInstance == null) {
                    mInstance = new CallBackManager();
                }
            }
        }
        return mInstance;
    }

    private IPluginCallback mCallBack;

    public void setCallback(IPluginCallback callback) {
        mCallBack = callback;
    }

    /**
     * 回调信息
     *
     * @param code
     * @param data
     */
    public static void makeCallBack(int code, Object data) {
        Message msg = new Message();
        msg.what = code;
        msg.obj = data;
        getInstance().mCallBack.onCallBack(msg);
    }

    public static void makeCallBack(int code) {
        makeCallBack(code, null);
    }

    public static void makeCallBack(Message msg) {
        getInstance().mCallBack.onCallBack(msg);
    }

}
