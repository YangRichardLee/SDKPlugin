package com.lion.plugin.sdkplugin.sdk.users;

import android.os.Message;

import com.lion.plugin.pluginlibs.IPluginCallback;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;

public class UserControl {

    private static final String TAG = UserControl.class.getName();

    private static IPluginCallback userCallback = new IPluginCallback() {
        @Override
        public void onCallBack(Message message) {
            switch (message.what){



                default:
                    break;
            }
        }
    };

    /**
     * 用户相关操作的回调
     *
     * @param code
     */
    public static void sendMsg(int code) {

        sendMsg(code, null);
    }

    /**
     * 用户相关操作的回调
     *
     * @param code
     * @param object
     */
    public static void sendMsg(final int code, final Object object) {

        PluginPlatform.getInstance().getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = code;
                msg.obj = object;
                userCallback.onCallBack(msg);
            }
        });
    }


}
