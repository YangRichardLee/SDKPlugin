package com.lion.plugin.sdkplugin.sdk.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.lion.plugin.pluginlibs.AppConstants;
import com.lion.plugin.pluginlibs.BaseFragment;
import com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 配置静态类
 * Created by Eleven on 16/4/23.
 */
public class Config {
    private static boolean isInit;
    private static String gameId;
    private final static String HOST = "https://www.gm88.com/api/index.php";
    private final static String HOST1 = "https://www.gm88.com/index.php";    //第三方帐号接口
    private final static String HOST1DEBUG = "https://demo.gm88.com/index.php";    //第三方帐号接口
    private final static String DEBUGHOST = "https://demo.gm88.com/api/index.php";    //调试使用的接口
    private final static String PAYBILL = "https://m.gm88.com/index.php?app=member&act=pay&sid=";         //充值地址
    private final static String PAYBILLDEBUG = "https://demo.gm88.com/index.php?app=member&act=pay&sid=";     //充值地址 测试环境
    private final static String SMS_PHONE_NUMBER = "106905292018";                  //短信注册的号码

    private static final String GSS_URL_RELEASE = "https://m.gm88.com/";
    private static final String GSS_URL_DEBUG = "https://demo.gm88.com/";


    private static boolean isDebug = false;
    private static String packageName = "";

    private static Application application;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        Config.isDebug = isDebug;
    }

    public static String getApiHost() {
        return isDebug ? DEBUGHOST : HOST;
    }

    public static String getThirdLoginHost() {
        return isDebug ? HOST1DEBUG : HOST1;
    }

    public static String getPaybill() {
        return isDebug ? PAYBILLDEBUG : PAYBILL;
    }

    public static String getSmsPhoneNumber() {
        return SMS_PHONE_NUMBER;
    }

    private static Context mContext;

    public static String getGameId() {
        return gameId;
    }

    public static void setGameId(Context context,String gameId) {
        Config.gameId = gameId;
        Config.isInit = true;
        mContext = context;
        Config.packageName = mContext.getPackageName();
    }


    public static String getPackageName() {
        return packageName;
    }

    public static boolean isInitPlatform() {
        return isInit;
    }


    public static String getCreatOrderApiUrl() {
        return getGssUrl() + "api/index.php";
    }


    public static String getGssUrl() {
        if (isDebug()) {
            return GSS_URL_DEBUG;
        } else {
            return GSS_URL_RELEASE;
        }
    }
    /**
     * 渠道id
     *
     * @return
     */
    @NonNull
    public static String getPromote() {
        String channel = "0";

        ApplicationInfo appinfo = mContext.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("META-INF/gmchannel")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] split = ret.split("_");
        if (split != null && split.length >= 2) {
            return ret.substring(split[0].length() + 1);
        } else {
            return "0";
        }
    }

    /**
     * @param thirdSdkName
     * @return
     */

    public static boolean isAccessThirdSdk(Context context, String thirdSdkName, String allthirdsdk) {
        if (allthirdsdk.length() > 10) {
            String third = allthirdsdk.substring(10);
            String[] split = third.split("_");
            List<String> thirdsdk = Arrays.asList(split);
            if (thirdsdk.size() > 0) {
                for (String sdk : thirdsdk) {
                    if (sdk.equals(thirdSdkName)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;


    }


    /**
     * 判断是否有META-INF/third_sdk文件.读取其文件名
     *
     * @return third_sdk_list
     */
    @NonNull
    public static List<String> getThirdSdkList(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("META-INF/third_sdk")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ret.length() > 10) {
            String thirdSdkName = ret.substring(10);
            String[] split = thirdSdkName.split("_");
            return Arrays.asList(split);
        } else {
            return null;
        }
    }

    /**
     * 拼接url
     *
     * @param url 服务器地址
     * @param map map数据
     * @return String 拼接后的url
     */
    public static String buildUrl(String url, Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return url;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        int i = 0;
        for (String key : map.keySet()) {
            if (i == 0) {
                sb.append("?");
            } else {
                sb.append("&");
            }
            sb.append(key).append("=").append(map.get(key));
            i++;
        }

        return sb.toString();
    }


    //获取当前sdk版本号
    public static String getSDKVersion() {
        String version = "3.6.2";
//        try {
//            version = mContext.getPackageManager().getApplicationInfo(
//                    mContext.getPackageName(),
//                    PackageManager.GET_META_DATA).metaData.getString("game_sdk_version_guaimao", "1.0.0");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (version == null)
//            version = "1.0.0";
        return version;
    }


    public static int getLayoutByName(String layoutName) {
        return mContext.getResources().getIdentifier(layoutName, "layout", packageName);
    }

    public static int getIdByName(String idName) {
        return mContext.getResources().getIdentifier(idName, "id", packageName);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static Application getApplication() {
        return application;
    }

    public static void setApplication(Application application) {
        Config.application = application;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public Context getContext() {
        return mContext;
    }


    public static void startPluginActivity(Context context, String activity){
        startPluginActivity(context,activity,null);
    }

    public static void startPluginActivity(Context context, String activity, Bundle bundle){

        Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
        intent.putExtra(AppConstants.EXTRA_DEX_PATH, PluginPlatform.getInstance().getDexPath());
        intent.putExtra(AppConstants.PROXY_ACTIVITY, activity);
        if (bundle != null){
            intent.putExtra(AppConstants.EXTRA_BUNDLE, bundle);
        }
        context.startActivity(intent);
    }


    public static void startPluginFragment(Context context, String fragment) {
        startPluginFragment(context,fragment,  null);
    }

    public static void startPluginFragment(Context context,String fragment, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(AppConstants.PROXY_VIEW_ACTION);
        intent.putExtra(AppConstants.EXTRA_DEX_PATH, PluginPlatform.getInstance().getDexPath());
        intent.putExtra(AppConstants.PROXY_FRAGMENT, fragment);
        if (bundle != null){
            intent.putExtra(AppConstants.EXTRA_BUNDLE, bundle);
        }
        context.startActivity(intent);
    }

    public static void startPluginFragment(Context context,BaseFragment nowFragment, BaseFragment targetFragment, Bundle args){

        Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
        intent.putExtra(AppConstants.EXTRA_DEX_PATH, PluginPlatform.getInstance().getDexPath());
        intent.putExtra(AppConstants.PROXY_FRAGMENT, targetFragment.getClass());
        if (args != null){
            intent.putExtra(AppConstants.EXTRA_BUNDLE, args);
        }
        context.startActivity(intent);
    }

}
