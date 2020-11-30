package com.lion.plugin.platform;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.lion.plugin.pluginlibs.AppConstants;
import com.lion.plugin.pluginlibs.IPluginCallback;
import com.lion.plugin.pluginlibs.ISdkMethod;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import dalvik.system.DexClassLoader;

public class Platform {

    private static Platform instance;
    private static File file;
    private Object mInstancePlatform;
    private Class<?> mLocalClass;
    private AssetManager mAssetManager;
    private Resources mResources;
    private LayoutInflater mLayoutInflater;
    private Resources.Theme mTheme;

    protected String mDexPath;
    protected ClassLoader dexClassLoader;

    private static ISdkMethod iSdkMethod;

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public AssetManager getmAssetManager() {
        return mAssetManager;
    }

    public Resources getmResources() {
        return mResources;
    }


    public Resources.Theme getmTheme() {
        return mTheme;
    }


    public String getmDexPath() {
        return mDexPath;
    }

    public void setmDexPath(String mDexPath) {
        this.mDexPath = mDexPath;
    }

    public ClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public void setDexClassLoader(ClassLoader dexClassLoader) {
        this.dexClassLoader = dexClassLoader;
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public Platform() {

    }


    public void initPlugin(final Application application) {
        Utils.copy(application, new Utils.Copy() {
            @Override
            public void copySucc() {
                file = new File(application.getCacheDir().getPath(), Utils._FILESAVENAME);
                setmDexPath(file.getAbsolutePath());
                loadClassLoaderAndResource(application);
                setApplication(application);
                Log.e("PluginPlatform", "插件复制完成");
            }
        });
    }

    public void setPluginPath(String pluginPath) {
        try {
            Method setPluginPath = mLocalClass.getDeclaredMethod("setDexPath", String.class);
            setPluginPath.invoke(mInstancePlatform, pluginPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void loadClassLoaderAndResource(Context context) {
        File dexOutputDir = context.getDir("dex", Context.MODE_PRIVATE);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();

        String librarySearchPath = FileUtils.copySo(context, file.getAbsolutePath());

        dexClassLoader = new DexClassLoader(mDexPath,
                dexOutputPath, librarySearchPath, context.getClassLoader());
        setDexClassLoader(dexClassLoader);
        try {
            mAssetManager = AssetManager.class.newInstance();
            Method addAssetPath = mAssetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(mAssetManager, mDexPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resources superRes = context.getResources();
        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(context.getTheme());
        Log.e("PluginPlatform", "loadClassLoaderAndResource");
    }

    /**
     * 初始化
     *
     * @param application
     */
    public void setApplication(Application application) {
        try {
            //反射出插件的Application对象
            mLocalClass = dexClassLoader.loadClass("com.lion.plugin.sdkplugin.sdk.platform.PluginPlatform");
            Constructor<?> localConstructor = mLocalClass.getConstructor();
            mInstancePlatform = localConstructor.newInstance();
            iSdkMethod = (ISdkMethod) mInstancePlatform;
            iSdkMethod.setApplication(application);
            iSdkMethod.setDexPath(file.getAbsolutePath());
            iSdkMethod.setResources(mResources);
            mLayoutInflater = PluginLayoutInflater.from(application,dexClassLoader);
            iSdkMethod.setLayoutInflater(PluginLayoutInflater.from(application,dexClassLoader));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pluginOnCreate() {
        Class clazz = null;
        try {
            clazz = dexClassLoader.loadClass("com.lion.plugin.sdkplugin.sdk.platform.PluginApplication");
            Application application = (Application) clazz.newInstance();
            application.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainActivity(Activity activity, IPluginCallback iPluginCallback) {
        iSdkMethod.setMainActivity(activity, iPluginCallback);
    }

    /**
     * 登录
     */
    public void login() {
        try {
            if (iSdkMethod != null) {
                iSdkMethod.login();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示登录页面登录
     */
    public void showLoginView() {
        try {
            if (iSdkMethod != null) {
                iSdkMethod.showLoginView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResume() {
        if (iSdkMethod != null) {
            iSdkMethod.onResume();
        }
    }

    public void showFloatView() {
        if (iSdkMethod != null) {
            iSdkMethod.showFloatView();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (iSdkMethod != null) {
            iSdkMethod.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void attachBaseContext(Application myApp) {
        initPlugin(myApp);
    }

    public void initApplication() {
        pluginOnCreate();
    }
}
