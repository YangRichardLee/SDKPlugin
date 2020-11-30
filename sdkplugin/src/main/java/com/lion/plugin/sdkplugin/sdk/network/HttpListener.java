package com.lion.plugin.sdkplugin.sdk.network;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpListener<T> {
    public abstract void onSuccess(T response);

    public abstract void onFail(String msg);

    public Class<?> getGenericType() {
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

}
