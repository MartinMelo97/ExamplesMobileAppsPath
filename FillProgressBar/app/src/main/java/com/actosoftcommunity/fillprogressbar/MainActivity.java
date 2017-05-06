package com.actosoftcommunity.fillprogressbar;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    ProgressBar pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pr = (ProgressBar) findViewById(R.id.progressBar);
        final long total = 6000;
        final long period = 1000;
        CountDownTimer countDownTimer = new CountDownTimer(total, period) {
            @Override
            public void onTick(long until)
            {
                long val = (6 - (until/1000))*20;

                //Toast.makeText(MainActivity.this, ""+val, Toast.LENGTH_SHORT).show();
                int valint = (int) val;
                //Toast.makeText(MainActivity.this, ""+valint, Toast.LENGTH_SHORT).show();
                pr.setProgress(valint);
            }

            @Override
            public void onFinish()
            {
                Toast.makeText(MainActivity.this, "Puto", Toast.LENGTH_SHORT).show();
            }

        }.start();
    }
}
