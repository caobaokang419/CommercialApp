/*
 * Copyright (C) 2015-present, Ant Financial Services Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gary.commercial.util;


import android.os.Build;

/**
 * Created by lezhou.wyl on 2018/1/11.
 */

public class MiscUtil {
    private static final String TAG = "MiscUtil";

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            CLog.e(TAG, "Sleep cause Exception: " + e);
        }
    }

    public static String generateSwipeCmd(int fromX, int fromY, int toX, int toY, long duration) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return "input swipe " + fromX + " " + fromY + " " + toX + " " + toY;
        } else {
            return "input swipe " + fromX + " " + fromY + " " + toX + " " + toY + " " + duration;
        }
    }

    public static String stackTraceToString(StackTraceElement[] stackTraceElements) {
        if (stackTraceElements == null || stackTraceElements.length <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stackTraceElements) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String getCurrentStrackTraceString() {
        return stackTraceToString(Thread.currentThread().getStackTrace());
    }

    public interface LogCallback {
        void onLogFinished();
    }
}
