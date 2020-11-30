package com.lion.plugin.sdkplugin.sdk.uitils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lion.plugin.sdkplugin.sdk.constants.SPConstants;
import com.lion.plugin.sdkplugin.sdk.modle.AppInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eleven
 * on 2017/6/12 上午8:34.
 */

public class AppInfoUtils {

    static String TAG = "AppInfoUtils";


    public AppInfoUtils() {
        // TODO Auto-generated constructor stub
//        this.context = Platform.getInstance().getContext();
//        pm = this.context.getPackageManager();
    }

    /**
     * 读取SP中字段,判断是否显示快速登录和第三方登录方式
     *
     * @param quickLogin
     * @param otherwaysLogin
     */

    public static void showLoginTypes(TextView quickLogin, LinearLayout otherwaysLogin) {
//        String loginTypes = SharedPreferencesUtil.getString(SPConstants.GM_SDK_NEED_LOGIN_TYPES, "");
//        if (loginTypes.length() > 2) {
//            if (loginTypes.contains("fast")) {
//                quickLogin.setVisibility(View.VISIBLE);
//            }
//            if (loginTypes.contains("qq") || loginTypes.contains("weibo") || loginTypes.contains("phone")) {
//                otherwaysLogin.setVisibility(View.VISIBLE);
//            }
//        }
    }

    /**
     * 读取SP中数据,判断其他登录方式中,需要显示哪几种登录方式
     *
     * @param qqlogin
     * @param weibologin
     * @param phonelogin
     */
    public static void showOtherWaysLoginTypes(RelativeLayout qqlogin, RelativeLayout weibologin, RelativeLayout phonelogin) {
        String loginTypes = SharedPreferencesUtil.getString(SPConstants.GM_SDK_NEED_LOGIN_TYPES, "");
        if (loginTypes.length() > 2) {
            if (loginTypes.contains("qq")) {
                qqlogin.setVisibility(View.VISIBLE);
            }
            if (loginTypes.contains("weibo")) {
                weibologin.setVisibility(View.VISIBLE);
            }
            if (loginTypes.contains("phone")) {
                phonelogin.setVisibility(View.VISIBLE);
            }
        }
    }


    public static void setETHindAndFocusable(EditText editText, String firstNum) {
        editText.setHint("验证码以" + firstNum + "开头");
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    /**
     * 检查指定包名APP是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        ULogUtil.d(TAG, "检查APP是否安装.....");
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    /**
     * 从assert中复制文件到指定目录
     *
     * @param myContext
     * @param ASSETS_NAME
     * @param savePath
     * @param saveName
     */
    public static void copy(Context myContext, String ASSETS_NAME,
                            String savePath, String saveName) {
        ULogUtil.d(TAG, "从assert中复制文件" + saveName + "到指定目录...." + savePath);
        String filename = savePath + "/" + saveName;
        File dir = new File(savePath);
        // 如果目录不中存在，创建这个目录
        if (!dir.exists())
            dir.mkdir();
        try {
            if (!(new File(filename)).exists()) {
                InputStream is = myContext.getResources().getAssets()
                        .open(ASSETS_NAME);
                FileOutputStream fos = new FileOutputStream(filename);
                byte[] buffer = new byte[7168];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void installApp(String filePath, Context context) {
        if (context != null) {
            File apkfile = new File(filePath);
            if (!apkfile.exists()) {
                return;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".gmfileprovider", apkfile);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
        }
    }



    /**
     * 获取apk文件的版本号
     *
     * @param context
     * @param apkPath
     * @return versionCode
     */
    public static int getApkVersion(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            return info.versionCode;
        }
        return 0;
    }

    /**
     * 获取程序的版本号
     * @param context
     * @param packname
     * @return getAppVersion
     */
    public static int getAppVersion(Context context, String packname) {

        try {
            PackageInfo packinfo = context.getPackageManager().getPackageInfo(packname, 0);
            return packinfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return 0;
    }

    public static String getTopActivity(Context context){
        // 获取activity任务栈
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        // 完整类名 com.haofang.testapp.ui.mobile.activity.WebsiteLoginActivity
        String className = info.topActivity.getClassName();

        return className;
    }


}