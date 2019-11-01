package com.gary.commercial.banner;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;


/**
 * Created by GaryCao on 2019/08/03.
 */
public class BannerLoaderManager {
    private static final String TAG = "BannerLoaderManager";
    private static BannerLoaderManager instance;
    private HandlerThread handlerThread;
    private Handler workHandler;

    private static final int EVT_LOADER_OFFLINE_IMAGE = 1;
    private static final int EVT_REQUEST_ONLINE_IMAGE = 2;

    public static synchronized BannerLoaderManager getInstance() {
        if (instance == null) {
            instance = new BannerLoaderManager();
        }
        return instance;
    }

    private BannerLoaderManager() {
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
                case EVT_LOADER_OFFLINE_IMAGE:
                    loaderOfflineImages();
                    break;
                case EVT_REQUEST_ONLINE_IMAGE:
                    requestOnlineImages();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private void loaderOfflineImages() {

    }

    private void requestOnlineImages() {

    }
}
