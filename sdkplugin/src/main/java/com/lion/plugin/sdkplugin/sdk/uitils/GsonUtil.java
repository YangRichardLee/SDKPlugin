package com.lion.plugin.sdkplugin.sdk.uitils;


import com.google.gson.Gson;

/**
 * Created by eleven
 * on 2016/12/19 上午11:57.
 */

public class GsonUtil {

    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) throws Exception {
        Gson gson = new Gson();
        T result = null;
        result = gson.fromJson(jsonData, type);
        return result;
    }
}
