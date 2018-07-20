package com.app1.venkatesh.eggtimers;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar Timerset;
    boolean countActive = false;
    Button EggButton;
    CountDownTimer countDownTimer;


    public void resetTimer() {
        timerTextView.setText("0:30");
        Timerset.setProgress(30);
        Timerset.setEnabled(true);
        countDownTimer.cancel();
        EggButton.setText("Go!");
        countActive = false;
    }
    public void buttonclick(View view){

        if (countActive){

            resetTimer();

        }else {
            countActive = true;
            Timerset.setEnabled(false);
            EggButton.setText("STOP!");
            Log.i("button pressed", "nice");
            countDownTimer = new CountDownTimer(Timerset.getProgress() * 1000 + 100, 1000) {
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stream);
                    mediaPlayer.start();
                    resetTimer();
                    Log.i("we're are done!", " Timer all done ");
                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int min = secondsLeft / 60;
        int seconds = secondsLeft - (min * 60);

        String SecondString = Integer.toString(seconds);
        if (seconds <= 9){
            SecondString = "0" + SecondString;
        }

        timerTextView.setText(Integer.toString(min) + ":" + SecondString);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timerset = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.TimerTextView);
        EggButton = findViewById(R.id.EggButton);

        Timerset.setMax(600);
        Timerset.setProgress(30);

        Timerset.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
