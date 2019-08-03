package com.gary.commercial.util;

import android.os.Handler;
import android.os.Looper;


/**
 * Created by GaryCao on 2019/08/03.
 */
public class HandlerUtil {
    private final static Handler uiHandler = new Handler(Looper.getMainLooper());

    public static boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isUIThread()) {
            runnable.run();
        } else {
            uiHandler.post(runnable);
        }
    }
}
