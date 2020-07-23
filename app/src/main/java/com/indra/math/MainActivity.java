package com.indra.math;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button b1,b2,b3,sub;
    private boolean running;
    private long offset;

    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private String mUsername;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseRefrence;

    private ValueEventListener eventListener;

    TextView ta,tb,ts,fs,hs;
    Map<String,Integer> map;
    int a,b,c,s;
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        mMessageDatabaseRefrence = mFirebaseDatabase.getReference().child("score");



        chronometer = findViewById(R.id.c1);
        chronometer.setFormat("Time : %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        //  chronometer.setCountDown(true);



        b1 = findViewById(R.id.start);
        b2 = findViewById(R.id.pause);
        b3 = findViewById(R.id.reset);

        ta = findViewById(R.id.a);
        tb = findViewById(R.id.b);
        ts = findViewById(R.id.score);
        fs = findViewById(R.id.finalscore);
        hs = findViewById(R.id.topscore);

        sub = findViewById(R.id.submit);
        ed = findViewById(R.id.res);


        map = new HashMap<>();

        running = false;
        offset = 0;
        s=0;
        sub.setClickable(false);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, play.class);
                startActivity(intent);
//                if(!running)
//                {
//                    chronometer.setBase(SystemClock.elapsedRealtime()-offset);
//                    chronometer.start();
//                    running = true;
//                    Random rand = new Random();
//                    int int_randoma = rand.nextInt(10);
//                    int int_randomb = rand.nextInt(10);
//                    ta.setText(String.valueOf(int_randoma));
//                    tb.setText(String.valueOf(int_randomb));
//                    sub.setClickable(true);
//                    ed.setText("");
//
//                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(running)
                {
                    chronometer.stop();
                    offset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    running = false;


                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    offset=0;
                    chronometer.stop();
                }
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

                    fs.setText(String.valueOf(s));
                    sub.setClickable(false);
                    s = 0;
                    mMessageDatabaseRefrence.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace('.',',')).setValue(fs.getText());


                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                a = Integer.valueOf(ta.getText().toString());
                b = Integer.valueOf(tb.getText().toString());
                if(ed.getText().length()!=0)
                c = Integer.valueOf(ed.getText().toString().trim());
                if(ed.getText().length()!=0 && c == (a+b) )
                {
                    s++;
                    ts.setText(String.valueOf(s));
                    ed.setText("");
                    Random rand = new Random();
                    int int_randoma = rand.nextInt(10);
                    int int_randomb = rand.nextInt(10);
                    ta.setText(String.valueOf(int_randoma));
                    tb.setText(String.valueOf(int_randomb));
                }
                else
                {
                    ed.setText("");
                    ed.setError("Wrong");

                }

            }
        });

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                map.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                  //  String username = ds.child("username").getValue(String.class);
                    String email = ds.getKey().toString();
                    String score = dataSnapshot.child(email).getValue(String.class);

                    map.put(email,Integer.valueOf(score));
//
//                    if(firebaseUser.getEmail().equals(email))
//                    {
//                        t1.setText(username);
//                        t2.setText(email);
//
//                        FirebaseMessaging.getInstance().subscribeToTopic("/topics/"+username);
//
//                        // FirebaseMessaging.getInstance().subscribeToTopic("news");
//                    }
//
//
//                    allusers.add(new User("Name",username,email,token));

                }


                hs.setText(map.toString());
               // UserAdapter adapter = new UserAdapter(getBaseContext(),allusers);

              //  listView.setAdapter(adapter);








            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        mMessageDatabaseRefrence.addValueEventListener(eventListener);


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(MainActivity.this, "You're now signed in", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                //startActivity(new Intent(getApplicationContext(),SignIn.class));
                // finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}