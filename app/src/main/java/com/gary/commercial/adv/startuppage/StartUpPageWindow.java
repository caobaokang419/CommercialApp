package com.gary.commercial.adv.startuppage;


import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gary.commercial.R;
import com.gary.commercial.util.Logger;

/**
 * Created by GaryCao on 2019/08/03.
 */
public class StartUpPageWindow {
    private static final String TAG = "StartUpPageWindow";

    private Context context;
    private WindowManager windowManager;
    private WindowManager.LayoutParams startupPageViewParams;
    private StartUpPageView startUpPageView;

    public StartUpPageWindow(Context context) {
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        setupLayoutParams();
    }

    private void setupLayoutParams() {
        if (startupPageViewParams == null) {
            startupPageViewParams = new WindowManager.LayoutParams();

            startupPageViewParams.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
            startupPageViewParams.format = PixelFormat.RGBA_8888;

            startupPageViewParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            startupPageViewParams.gravity = Gravity.LEFT | Gravity.TOP;
            startupPageViewParams.x = 0;
            startupPageViewParams.y = 0;
            startupPageViewParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            startupPageViewParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
    }

    private void createStartupPageView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout tmpRoot = new LinearLayout(context);
        startUpPageView = (StartUpPageView) inflater.inflate(R.layout.startup_page_window, tmpRoot, false);
        startUpPageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                return false;
            }
        });
    }

    public void showWindow() {
        Logger.d(TAG, "showWindow ");
        if (startUpPageView == null) {
            createStartupPageView();
        }
        windowManager.addView(startUpPageView, startupPageViewParams);
        //startUpPageView.show();
    }

    public void hideWindow() {
        Logger.d(TAG, "hideWindow ");
        windowManager.removeView(startUpPageView);
    }
}