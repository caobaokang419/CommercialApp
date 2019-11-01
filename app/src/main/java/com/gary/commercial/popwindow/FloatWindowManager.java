package com.gary.commercial.popwindow;


import android.content.Context;

import com.gary.commercial.util.CLog;

/**
 * Created by GaryCao on 2019/08/03.
 */
public class FloatWindowManager {
    private static final String TAG = "FloatWindowManager";
    private static FloatWindowManager instance;
    private Context context;
    private FloatWindow floatWindow;

    public static synchronized FloatWindowManager getInstance(Context context) {
        if (instance == null) {
            instance = new FloatWindowManager(context);
        }
        return instance;
    }

    private FloatWindowManager(Context context) {
        this.context = context;
    }

    public void showFloatWindow() {
        CLog.d(TAG, "showFloatWindow ");
        if (floatWindow == null) {
            floatWindow = new FloatWindow(context);
        }
        floatWindow.showWindow();
    }

    public void hideFloatWindow() {
        CLog.d(TAG, "hideFloatWindow ");
        if (floatWindow != null) {
            floatWindow.hideWindow();
        }
    }
}
