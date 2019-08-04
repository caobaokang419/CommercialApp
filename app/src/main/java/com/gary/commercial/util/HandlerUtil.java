package com.gary.commercial.util;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;


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

    public static void addIdleHandler(MessageQueue.IdleHandler idleHandler) {
        Looper.myQueue().addIdleHandler(idleHandler);
    }

    class DemoIdleKeep implements MessageQueue.IdleHandler {
        /**
         * @return true: 保持在UIHandler中 false: 执行后被remove掉
         */
        @Override
        public boolean queueIdle() {
            return true;
        }

    }

    class DemoIdleOnce implements MessageQueue.IdleHandler {
        @Override
        public boolean queueIdle() {
            return false;
        }
    }
}
