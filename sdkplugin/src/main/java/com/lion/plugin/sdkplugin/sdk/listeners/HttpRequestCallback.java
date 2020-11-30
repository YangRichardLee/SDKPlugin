package com.lion.plugin.sdkplugin.sdk.listeners;

import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;
import com.lion.plugin.sdkplugin.sdk.base.BaseResult;
import com.lion.plugin.sdkplugin.sdk.uitils.ULogUtil;


/**
 * 请求接口回调
 * Created by Eleven on 2016/8/28.
 */
public class HttpRequestCallback implements IHttpRequestCallback {
    private static final String TAG = HttpRequestCallback.class.getName();

    @Override
    public void onSuccess(Object data) {
    }

    @Override
    public void onFail(String msg) {
        if (msg == null || msg.isEmpty()){
            return;
        }
        ULogUtil.d(TAG,"[onFail...]"+msg);
        if ("用户未登录".equals(msg)) {
            PluginPlatform.getInstance().logout();
        }
        if (msg.contains("封号")){
//            GameInfoManager.roleCaveatOrSelNumDialog("封号通知",msg);
        }
    }

    @Override
    public void onResponse(Object data) {
        BaseResult<Object> baseResult = (BaseResult<Object>) data;
        if (baseResult != null && baseResult.status) {
            onSuccess(data);
        } else {
            if (baseResult != null && baseResult.errortext != null)
                onFail(baseResult.errortext);
            else
                onFail("出错啦~");
        }
    }

    @Override
    public void onError(String msg) {

    }
}
