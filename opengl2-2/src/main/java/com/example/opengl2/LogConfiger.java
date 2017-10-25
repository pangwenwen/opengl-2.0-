package com.example.opengl2;

import android.util.Log;

/**
 * Created by hua.pang on 2017/10/24.
 */

public class LogConfiger{
    public static boolean ON = true;
    private static String TAG = "PH_opengl2:";

    public static int v(String tag, String msg) {
        return Log.v(TAG+tag,msg);
    }

    public static int d(String tag, String msg) {
        return Log.d(TAG+tag,msg);
    }

    public static int i(String tag, String msg) {
        return Log.i(TAG+tag,msg);
    }

    public static int w(String tag, String msg) {
        return Log.w(TAG+tag,msg);
    }

    public static int e(String tag, String msg) {
        return Log.e(TAG+tag,msg);
    }

}
