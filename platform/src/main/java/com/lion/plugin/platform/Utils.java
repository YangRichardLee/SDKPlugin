package com.lion.plugin.platform;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author weishu
 * @date 16/3/29
 */
public class Utils {

    /**
     * 把Assets里面得文件复制到 /data/data/files 目录下
     *
     * @param context
     * @param sourceName
     */
    public static void extractAssets(Context context, String sourceName) {
        AssetManager am = context.getAssets();
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = am.open(sourceName);
            File extractFile = context.getFileStreamPath(sourceName);
            fos = new FileOutputStream(extractFile);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSilently(is);
            closeSilently(fos);
        }

    }

    // --------------------------------------------------------------------------
    private static void closeSilently(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Throwable e) {
            // ignore
        }
    }

    public static final String _FILENAME = "sdkplugin-release.apk";
    public static final String _FILESAVENAME = "sdkplugin-release.apk";

    public static void copy(Context context, Copy copy) {
        copy(context, _FILENAME, context.getCacheDir().getPath(), _FILESAVENAME, copy);
    }

    public static void copy(Context myContext, String ASSETS_NAME,
                     String savePath, String saveName, Copy copy) {
        Log.d("TAG", "从assert中复制文件" + saveName + "到指定目录...." + savePath);
        String filename = savePath + "/" + saveName;
        File fi = new File(filename);
        File dir = new File(savePath);
        // 如果目录不中存在，创建这个目录
        if (!dir.exists()){
            dir.mkdir();
        }
        if (fi.exists()){
            fi.delete();
        }
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
            if (copy != null) {
                copy.copySucc();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    interface Copy {
        void copySucc();

    }
}
