package com.example.poto.playmymusic;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {

    private static int DURATION = 4000;
    private Handler handler = new Handler();
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView poto = (ImageView) findViewById(R.id.potoLogo);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished/1000 == 1){

                    YoYo.with(Techniques.FadeIn)
                            .duration(3000)
                            .repeat(1)
                            .playOn(findViewById(R.id.potoLogo));
                    poto.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,CategoryActivity.class));
            }
        },DURATION);
    }
}
