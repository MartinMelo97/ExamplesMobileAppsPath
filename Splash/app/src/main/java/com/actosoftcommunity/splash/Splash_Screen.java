package com.actosoftcommunity.splash;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash_Screen extends AppCompatActivity {

    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        /*Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(5000);
                    Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intento);
                    finish(); //onDestroy()
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
        */

        pb = (ProgressBar) findViewById(R.id.progressBar);
        final long total = 6000;
        final long period = 1000;

        CountDownTimer count = new CountDownTimer(total, period) {
            @Override
            public void onTick(long l) {

                long val = (6 - (l/1000))*20;
                int valint = (int) val;
                pb.setProgress(valint);
            }

            @Override
            public void onFinish() {
                Intent intento = new Intent(getApplicationContext(), MainActivity.class);
 finish()               y

            }
        }.start();
    }
}
