package com.lion.plugin.sdkplugin.sdk.listeners;

/**
 * Created by eleven
 * on 2016/12/20 下午3:43.
 */

public interface IHttpRequestCallback {

    void onSuccess(Object data);

    void onFail(String msg);

    void onResponse(Object data);

    void onError(String msg);
}
