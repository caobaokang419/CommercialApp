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
public class StartUpPageView extends View{
    private static final String TAG = "StartUpPageWindow";
    private Context context;

    public StartUpPageView(Context context) {
        super(context);
        this.context = context;
    }
}
