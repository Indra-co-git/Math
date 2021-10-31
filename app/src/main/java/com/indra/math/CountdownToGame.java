package com.indra.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CountdownToGame extends AppCompatActivity {

    //Declare timer
    CountDownTimer cTimer = null;

    TextView coundown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_to_game);
        coundown=findViewById(R.id.countdown);
        startTimer();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    //start timer function
    void startTimer() {
        cTimer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                coundown.setText("Game Start in: " + millisUntilFinished / 1000 +" sec");
            }
            public void onFinish() {
                Intent intent = new Intent(CountdownToGame.this, play.class);
                startActivity(intent);
                cancelTimer();
                finish();
            }
        };
        cTimer.start();
    }

    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
}