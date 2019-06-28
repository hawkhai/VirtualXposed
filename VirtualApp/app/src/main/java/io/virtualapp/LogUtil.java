package io.virtualapp;

import android.util.Log;

/**
 * Created by Superhai on 2019/5/21
 */
public class LogUtil {
    public final static void i(String msg) {
        Log.i("hawkhai", msg);
    }

    public final static void e(String msg) {
        Log.e("hawkhai", msg);
    }

    public final static void e(String tag, String msg) {
        Log.e("hawkhai", String.format("[%s] %s", tag, msg));
    }
}
