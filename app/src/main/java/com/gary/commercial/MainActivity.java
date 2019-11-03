package com.gary.commercial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gary.commercial.banner.BannerActivity;

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
    }

    public void startBannerAdv(View view){
        Intent intent = new Intent();
        intent.setClass(this,BannerActivity.class);
        startActivity(intent);
    }

    public void startAppInstallPageAdv(View view){

    }

    public void checkAndUpdateApk(View view){

    }

    public void login(View view){

    }
}
