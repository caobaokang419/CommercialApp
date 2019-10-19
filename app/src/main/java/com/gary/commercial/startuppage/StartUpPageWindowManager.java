package com.gary.commercial.startuppage;


import android.content.Context;

import com.gary.commercial.util.CLog;

/**
 * Created by GaryCao on 2019/08/03.
 */
public class StartUpPageWindowManager {
    private static final String TAG = "startPageWindowManager";
    private static StartUpPageWindowManager instance;
    private Context context;

    private StartUpPageWindow startUpPageWindow;

    public static synchronized StartUpPageWindowManager getInstance(Context context) {
        if (instance == null) {
            instance = new StartUpPageWindowManager(context);
        }
        return instance;
    }

    private StartUpPageWindowManager(Context context) {
        this.context = context;
    }

    public void showStartUpPageWindow() {
        CLog.d(TAG, "showStartUpPageWindow ");
        if (startUpPageWindow == null) {
            startUpPageWindow = new StartUpPageWindow(context);
        }
        startUpPageWindow.showWindow();
    }

    public void hideStartUpPageWindow() {
        CLog.d(TAG, "hideStartUpPageWindow ");
        if (startUpPageWindow != null) {
            startUpPageWindow.hideWindow();
        }
    }
}