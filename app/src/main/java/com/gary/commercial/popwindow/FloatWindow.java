package com.gary.commercial.popwindow;


import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gary.commercial.R;
import com.gary.commercial.util.CLog;

/**
 * Created by GaryCao on 2019/08/03.
 */
public class FloatWindow {
    private static final String TAG = "FloatWindow";

    private Context context;
    private WindowManager windowManager;
    private WindowManager.LayoutParams floatViewParams;
    private FloatView floatView;

    public FloatWindow(Context context) {
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        setupLayoutParams();
    }

    private void setupLayoutParams() {
        if (floatViewParams == null) {
            floatViewParams = new WindowManager.LayoutParams();

            floatViewParams.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
            floatViewParams.format = PixelFormat.RGBA_8888;

            floatViewParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            floatViewParams.gravity = Gravity.LEFT | Gravity.TOP;
            floatViewParams.x = 0;
            floatViewParams.y = 0;
            floatViewParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            floatViewParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
    }

    private void createFloatView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout tmpRoot = new LinearLayout(context);
        floatView = (FloatView) inflater.inflate(R.layout.float_window, tmpRoot, false);
        floatView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                return false;
            }
        });
    }

    public void showWindow() {
        CLog.d(TAG, "showWindow ");
        if (floatView == null) {
            createFloatView();
        }
        windowManager.addView(floatView, floatViewParams);
        //startUpPageView.show();
    }

    public void hideWindow() {
        CLog.d(TAG, "hideWindow ");
        windowManager.removeView(floatView);
    }
}