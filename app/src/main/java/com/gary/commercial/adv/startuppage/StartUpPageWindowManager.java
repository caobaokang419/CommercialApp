package com.gary.commercial.adv.startuppage;


/**
 * Created by GaryCao on 2019/08/03.
 */
public class StartUpPageWindowManager {
    private static final String TAG = "StartUpPageWm";
    private static StartUpPageWindowManager instance;

    public static synchronized StartUpPageWindowManager getInstance() {
        if (instance == null) {
            instance = new StartUpPageWindowManager();
        }
        return instance;
    }

}
