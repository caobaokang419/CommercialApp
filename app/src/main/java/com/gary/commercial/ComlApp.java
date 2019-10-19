package com.gary.commercial;

import android.app.Application;
import android.content.Context;

public class ComlApp extends Application {
    private static final String TAG = "ComlApp";
    private static Context context;

    @Override
    public void onCreate() {
        initContext(this);
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initContext(Context cont) {
        context = cont;
    }

    public static Context getContext() {
        return context;
    }
}
