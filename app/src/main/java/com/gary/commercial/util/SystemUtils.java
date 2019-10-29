package com.gary.commercial.util;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;

import java.util.Random;

public class SystemUtils {
    private static Random mRandom = new Random();

    public static boolean isScreenLocked(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean isScreenLight(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }
}
