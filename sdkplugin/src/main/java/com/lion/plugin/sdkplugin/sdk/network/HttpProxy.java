package com.lion.plugin.sdkplugin.sdk.network;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lion.plugin.sdkplugin.sdk.base.BaseResult;
import com.lion.plugin.sdkplugin.sdk.base.Config;
import com.lion.plugin.sdkplugin.sdk.listeners.HttpRequestCallback;
import com.lion.plugin.sdkplugin.sdk.uitils.GsonUtil;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * todo   主要用来代理所有的网络请求
 */
public class HttpProxy {

    private static OkHttpClient okHttpClient;
    private static HttpProxy mHttpProxy;
    private static Handler handler;
    public static final String DEFAULT_PARAMS_ENCODING = "UTF-8";


    public static HttpProxy getInstance() {
        if (mHttpProxy == null) mHttpProxy = new HttpProxy();
        if (okHttpClient == null) initOkHttp();
        return mHttpProxy;
    }


    private static void initOkHttp() {
        if (okHttpClient == null) okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        if (handler == null) handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 中断网络请求
     */
    public static void cancelRequest(Object ob) {
    }

    /**
     * 中断所有网络请求
     *
     * @param
     */
    public static void cancelAllRequest() {
    }


    /**
     * get 请求
     *
     * @param params 请求参数
     */
    public static Request get(final Map<String, String> params, final Class clzz,
                              final HttpRequestCallback callback) {

//        if (disposeNoNetworkRequest(callback)) {
//            return null;
//        }

        String url = Config.getApiHost();
        if (!URLUtil.isNetworkUrl(url)) {
            callback.onFail("url is error");
            return null;
        }


        Request request = null;
        final String allUrl = getUrlWithQueryString(url, params, DEFAULT_PARAMS_ENCODING);
//        if (params != null)
//            Log.i("HttpProxy", "请求get方式:" + params.get("action") + ":" + new JSON().toJson(params));
        request = new Request.Builder().url(allUrl).tag(allUrl).build();
        //得到Call对象
        initOkHttp();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                if (callback != null && handler != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(e.getMessage());
                            callback.onFail(e.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                try {
                    final String json = response.body().string();
                    Log.i("HttpProxy", allUrl + " 返回数据：" + json);
                    final BaseResult baseResult = (BaseResult) GsonUtil.parseJsonWithGson(json, BaseResult.class);
                    if (baseResult.status) {
//                        final Object data = GsonUtil.parseJsonWithGson(json, clzz);
                        if (callback != null && handler != null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onSuccess(json);
                                }
                            });
                        }
                    } else {
                        if (callback != null && handler != null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onFail(baseResult.errortext);
                                }
                            });
                        }
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    if (callback != null && handler != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError(e.getMessage());
                                callback.onFail(e.getMessage());
                            }
                        });
                    }
                }
            }
        });


        return request;
    }

    public static void post(String url, final Map<String, String> params, final Class clzz, final HttpRequestCallback callback) {

        if (disposeNoNetworkRequest(callback)) {
            return;
        }

        if (!URLUtil.isNetworkUrl(url)) {
            callback.onFail("url is error");
            return;
        }

        addCommonParam(params);
        if (params != null) {
            Log.i("HttpProxy", "请求:" + params.get("action") + ":" + new Gson().toJson(params));
        }
        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null)
                builder.add(URLEncoder.encode(key), params.get(key));
        }
        Request request = new Request.Builder().url(url).post(builder.build()).tag("").build();
        //得到Call对象
        initOkHttp();
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException error) {
                if (callback != null && handler != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFail(error.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                final String json = response.body().string();
                if (params != null)
                    Log.i("HttpProxy", params.get("action") + ":" + json);
                try {
                    final Object data = GsonUtil.parseJsonWithGson(json, clzz);
                    if (callback != null && handler != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    org.json.JSONObject jsonObject = new org.json.JSONObject(json);
                                    if (jsonObject.getBoolean("status")) {
                                        if (clzz != null) {
                                            callback.onSuccess(data);
                                        } else {
                                            callback.onSuccess(json);
                                        }
                                    } else {
//                                        callback.onError(jsonObject.getString("errortext"));
                                        callback.onFail(jsonObject.getString("errortext"));
                                    }
                                } catch (JSONException e) {
                                    callback.onFail(e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFail(e.getMessage());
                        }
                    });
                }
            }
        });
    }

    public static void post(final Map<String, String> params, final Class clzz, final HttpRequestCallback callback) {

        String url = Config.getApiHost();

        if (disposeNoNetworkRequest(callback)) {
            return;
        }

        if (!URLUtil.isNetworkUrl(url)) {
            callback.onFail("url is error");
            return;
        }

        addCommonParam(params);
        if (params != null) {
            Log.i("HttpProxy", "请求:" + params.get("action") + ":" + new Gson().toJson(params));
        }


        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null)
                builder.add(URLEncoder.encode(key), params.get(key));
        }
        Request request = new Request.Builder().url(url).post(builder.build()).tag("").build();
        //得到Call对象
        initOkHttp();
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException error) {
                if (callback != null && handler != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(error.getMessage());
//                            callback.onFail(error.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                final String json = response.body().string();
                if(json.isEmpty()){
                    if (callback != null && handler != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError("");
                            }
                        });
                    }
                    return;
                }
                if (params != null)
                    Log.i("HttpProxy", params.get("action") + ":" + json);
                try {
                    final Object data = GsonUtil.parseJsonWithGson(json, clzz);
                    if (callback != null && handler != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    org.json.JSONObject jsonObject = new org.json.JSONObject(json);
                                    if (jsonObject.getBoolean("status")) {
                                        if (clzz != null) {
                                            callback.onSuccess(data);
                                        } else {
                                            callback.onSuccess(json);
                                        }
                                    } else {
//                                        callback.onError(jsonObject.getString("errortext"));
                                        if (jsonObject.has("extra")) {
                                            callback.onFail(jsonObject.getString("extra"));
                                        } else {
                                            callback.onFail(jsonObject.getString("errortext"));
                                        }
                                    }
                                } catch (JSONException e) {
                                    callback.onFail(e.getMessage());
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFail(e.getMessage());
                        }
                    });
                }
            }
        });
    }


    /**
     * 公共参数
     *
     * @param map
     */
    private static void addCommonParam(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
//        map.put("oaid", GMSDK.getOaidss());
//        if (PluginDataUtils.getInstance().isWsy()) {
//            map.put("sdk_ver", "wsy");
//        }
    }




    /**
     * 预处理无网络状态下的请求
     *
     * @return ture 就是无网络且处理了回调
     */
    private static <T> boolean disposeNoNetworkRequest(HttpRequestCallback callback) {
//        int networkType = GlobalUtil.getNetworkType(Platform.getInstance().getContext());
//        if (networkType == GlobalUtil.NET_TYPE_NO) {
//            if (Platform.getInstance().getContext() != null) ToastHelper.toast(Platform.getInstance().getContext(), "Network Error");
//            callback.onFail("Network Error");
//            return true;
//        }
        return false;
    }


    /**
     * 将参数中的null转换成空字符串
     *
     * @param map
     * @return
     */
    private static Map<String, String> checkParams(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
            if (pairs.getValue() == null) {
                map.put(pairs.getKey(), "");
            }
        }
        return map;
    }


    public static String getUrlWithQueryString(String url,
                                               Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        if (params != null && params.size() > 0) {
            try {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (entry.getValue() != null) {
                        encodedParams.append(URLEncoder.encode(entry.getKey(),
                                paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode(entry.getValue(),
                                paramsEncoding));
                        encodedParams.append('&');
                    }
                }
            } catch (UnsupportedEncodingException uee) {
                throw new RuntimeException("Encoding not supported: "
                        + paramsEncoding, uee);
            }

            if (url.indexOf("?") == -1) {
                url += "?"
                        + encodedParams
                        .substring(0, encodedParams.length() - 1);
            } else {
                url += "&"
                        + encodedParams
                        .substring(0, encodedParams.length() - 1);
            }
        }

        return url;
    }


    /**
     * 加载网络图片
     *
     * @param url
     * @param imageView
     */
    public static void displayImage(String url, ImageView imageView) {

    }

}
