package com.gary.commercial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gary.commercial.adv.startuppage.StartUpPageWindowManager;


/**
 * Created by GaryCao on 2019/08/03.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startStartUpPageAdv(View view){
        StartUpPageWindowManager.getInstance(this).showStartUpPageWindow();
    }

    public void startBannerAdv(View view){

    }


    public void startAppInstallPageAdv(View view){

    }

    public void checkAndUpdateApk(View view){

    }

    public void login(View view){

    }
}
