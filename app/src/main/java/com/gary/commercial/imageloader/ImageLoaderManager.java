package com.gary.commercial.imageloader;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;


/**
 * Created by GaryCao on 2019/08/03.
 */
public class ImageLoaderManager {
    private static final String TAG = "ImageLoaderManager";
    private static ImageLoaderManager instance;
    private HandlerThread handlerThread;
    private Handler workHandler;

    private static final int EVT_INIT_LOADER_OFFLINE_IMAGE = 1;
    private static final int EVT_REQUEST_ONLINE_IMAGE = 2;


    public static synchronized ImageLoaderManager getInstance() {
        if (instance == null) {
            instance = new ImageLoaderManager();
        }
        return instance;
    }

    private ImageLoaderManager() {
        handlerThread = new HandlerThread("ImgLoader");
        handlerThread.start();
        workHandler = new WorkHandler(handlerThread.getLooper());
    }

    private class WorkHandler extends Handler {
        public WorkHandler(Looper loopper) {
            super(loopper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVT_INIT_LOADER_OFFLINE_IMAGE:
                    checkAndLoaderOfflineImages();
                    break;
                case EVT_REQUEST_ONLINE_IMAGE:
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private void checkAndLoaderOfflineImages() {

    }

    private void checkAndRequestOnlineImages() {

    }
}
