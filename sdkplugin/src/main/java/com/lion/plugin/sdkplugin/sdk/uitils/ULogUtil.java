package com.lion.plugin.sdkplugin.sdk.uitils;

/**
 * 打印log文件
 *
 * @author mili
 * @version 1.0
 */

import android.util.Log;

public class ULogUtil {

    public static final int LEVEL_ERROR = 0;
    public static final int LEVEL_WARING = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_DEBUG = 3;

    private static String stag = "gmsdk";
    private static int mLogLevel = LEVEL_DEBUG;

    public ULogUtil() {
    }

    public static void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }

    /**
     * 修改打印的TAG
     *
     * @param tag TAG的名字
     */
    public static void setTag(String tag) {
        Log.w("LOG", (new StringBuilder()).append("log tag [").append(stag).append("] be replaced to [").append(tag).append("]").toString());
        stag = tag;
    }

    /**
     * debug打印
     *
     * @param tag 一般是文件名字
     * @param msg 需要打印的信息
     */
    public static void d(String tag, String msg) {
        if (mLogLevel >= LEVEL_DEBUG) {
            //FIXME: 修改成 debug
            Log.d(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString());
        }
    }

    public static void d(String tag, String msg, Throwable t) {
        if (mLogLevel >= LEVEL_DEBUG) {
            Log.d(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString(), t);
        }
    }

    public static void i(String tag, String msg) {
        if (mLogLevel >= LEVEL_INFO) {
            Log.i(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString());
        }
    }

    public static void i(String tag, String msg, Throwable t) {
        if (mLogLevel >= LEVEL_INFO) {
            Log.i(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString(), t);
        }
    }

    public static void w(String tag, String msg) {
        if (mLogLevel >= LEVEL_WARING) {
            Log.w(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString());
        }
    }

    public static void w(String tag, String msg, Throwable t) {
        if (mLogLevel >= LEVEL_WARING) {
            Log.w(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString(), t);
        }
    }

    public static void e(String tag, String msg) {
        if (mLogLevel >= LEVEL_ERROR) {
            Log.e(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString());
        }
    }

    public static void e(String tag, String msg, Throwable t) {
        if (mLogLevel >= LEVEL_ERROR) {
            Log.e(stag, (new StringBuilder()).append("[").append(tag).append("] ").append(msg).toString(), t);
        }
    }

}
