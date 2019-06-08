package com.oculogx.itunes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.oculogx.itunes.R;

public class OculogxSplashActivity extends AppCompatActivity {


    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oculogx_splash);

        setSplash();
    }

    private void setSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent launchIntent = new Intent(OculogxSplashActivity.this, ItunesListViewActivity.class);
                startActivity(launchIntent);
                finish();

            }
        },SPLASH_DISPLAY_LENGTH);
    }

}
