package com.laisontech.videoplayer.util;

/**
 * 日志类
 */

public class Log {

    private static final String TAG = "DKPlayer";

    private static boolean isDebug = false;


    public static void d(String msg) {
        if (isDebug) {
            android.util.Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            android.util.Log.e(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            android.util.Log.i(TAG, msg);
        }
    }

    public static void setDebug(boolean isDebug) {
        Log.isDebug = isDebug;
    }
}
