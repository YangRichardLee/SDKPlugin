package com.lion.plugin.sdkplugin.sdk.precenter;

import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.modle.VersionCheckResultEntity;
import com.lion.plugin.sdkplugin.sdk.network.HttpProxy;
import com.lion.plugin.sdkplugin.sdk.uitils.GlobalUtil;

import java.util.HashMap;
import java.util.Map;

public class UserCenterPresenter {


    //检查版本
    public static void getCheckVersion(final HttpRequestCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "system.update");
        params.put("game_id", Config.getGameId());
        params.put("version", Config.getSDKVersion());
        params.put("imei", GlobalUtil.getIMEI(PluginPlatform.getInstance().getActivity()));
        params.put("mac", GlobalUtil.getLocalMacAddress(PluginPlatform.getInstance().getActivity()));

        HttpProxy.post(params, VersionCheckResultEntity.class, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object data) {
                super.onSuccess(data);
                callback.onSuccess(data);
            }

            @Override
            public void onFail(String msg) {
                super.onFail(msg);
                callback.onFail(msg);
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                callback.onError(msg);
            }
        });
    }


}
