package com.indra.math;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class play extends AppCompatActivity {

    Chronometer chronometer;
    Button start,returned,skip,sub,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,backspace;
    private boolean running;
    private long offset;
    String answer_input;

    int solved_val,appered_val,accuracy_val;

    TextView ta,tb,ed,solve,appere,accuracy;
    int a,b,c,s;
//    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        chronometer = findViewById(R.id.c1);
        chronometer.setFormat("Time : %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        //  chronometer.setCountDown(true);
        answer_input = "0";
        solved_val=0;
        appered_val=0;
        accuracy_val=0;


        start = findViewById(R.id.start);
//        pause = findViewById(R.id.pause);
//        reset = findViewById(R.id.reset);

        ta = findViewById(R.id.a);
        tb = findViewById(R.id.b);
        solve=findViewById(R.id.q_solved);
        appere=findViewById(R.id.q_appered);
        accuracy=findViewById(R.id.accuracy);
        ed = findViewById(R.id.res);

        sub = findViewById(R.id.submit);


        returned=findViewById(R.id.returned);
        skip=findViewById(R.id.skip);

//        keyboard cord part
        {
        ed = findViewById(R.id.res);

        sub = findViewById(R.id.submit);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);

        backspace = findViewById(R.id.back);


        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.length() == 1) {
                    answer_input = "0";
                } else
                    answer_input = answer_input.substring(0, answer_input.length() - 1);
                ed.setText(answer_input);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "1";
                else
                    answer_input += "1";
                ed.setText(answer_input);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "2";
                else
                    answer_input += "2";
                ed.setText(answer_input);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "3";
                else
                    answer_input += "3";
                ed.setText(answer_input);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "4";
                else
                    answer_input += "4";
                ed.setText(answer_input);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "5";
                else
                    answer_input += "5";
                ed.setText(answer_input);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "6";
                else
                    answer_input += "6";
                ed.setText(answer_input);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "7";
                else
                    answer_input += "7";
                ed.setText(answer_input);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "8";
                else
                    answer_input += "8";
                ed.setText(answer_input);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "9";
                else
                    answer_input += "9";
                ed.setText(answer_input);
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer_input.startsWith("0"))
                    answer_input = "0";
                else
                    answer_input += "0";
                ed.setText(answer_input);
            }
        });
    }


        running = false;
        offset = 0;
        s=0;
        sub.setClickable(false);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime()-offset);
                    chronometer.start();
                    running = true;
                    Random rand = new Random();
                    int int_randoma = rand.nextInt(10);
                    int int_randomb = rand.nextInt(10);
                    ta.setText(String.valueOf(int_randoma));
                    tb.setText(String.valueOf(int_randomb));
                    sub.setClickable(true);
                    ed.setText("");
                    answer_input="0";
                }
            }
        });

        //skip of question with no accuracy change
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(running)
                {
                    set_next_question();
                }
            }
        });
//        return to mainactivity(intro)
        returned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( play.this,MainActivity.class);
                startActivity(intent);
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime() - chronometer.getBase() >= 10000)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    running = false;
                    chronometer.stop();

                    sub.setClickable(false);
                    s = 0;
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running) {

                    if (ta.getText().toString().length() == 0 || tb.getText().toString().length() == 0 || ed.getText().toString().length() == 0) {
                        Toast.makeText(play.this, "Submit is wrong", Toast.LENGTH_SHORT).show();
                    } else {
                        a = Integer.valueOf(ta.getText().toString());
                        b = Integer.valueOf(tb.getText().toString());
                        c = Integer.valueOf(ed.getText().toString().trim());
                        answer_input = "0";
                        ed.setText(answer_input);
                        if (ed.getText().length() != 0 && c == (a + b)) {
                            s++;
                            ed.setText(answer_input);
                            solved_val += 1;

                            ed.setBackgroundColor(Color.parseColor("#94CAFBAB"));
                        } else {
                            ed.setText("");
                            ed.setError("Wrong");
                            ed.setBackgroundColor(Color.parseColor("#60F47C7C"));
                        }
                        appered_val++;

                        set_scored_accuracy_appared();
                        set_next_question();
//                        solve.setText("some");
                    }
                }
            }
        });


    }

    private void set_scored_accuracy_appared() {

        solve.setText(String.valueOf(solved_val));
        appere.setText(String.valueOf(appered_val));
        if(appered_val!=0)
        accuracy.setText(String.valueOf((solved_val*100/appered_val)));
        else
            accuracy.setText("0");
    }

    private void set_next_question() {
        Random rand = new Random();
        int int_randoma = rand.nextInt(10);
        int int_randomb = rand.nextInt(10);
        ta.setText(String.valueOf(int_randoma));
        tb.setText(String.valueOf(int_randomb));
    }


}