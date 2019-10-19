package com.gary.commercial.util;

import android.database.Cursor;

import java.io.Closeable;

/**
 * origin from xUtils3: https://github.com/wyouflf/xUtils3
 */
public class IOUtil {

    private IOUtil() {
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable ignored) {
                CLog.d(ignored.getMessage() + ignored);
            }
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable ignored) {
                CLog.d(ignored.getMessage() + ignored);
            }
        }
    }
}
