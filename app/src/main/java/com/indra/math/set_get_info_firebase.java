package com.indra.math;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;


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

public class set_get_info_firebase {

    public set_get_info_firebase() {
        Log.d("get_over_all_info_fire","------------------------cLASS IT ----------"+"-----");
    }


    static public GameDetailFirebase over_all_datail_class,in_time_limit_detail_class;

    static long overall_level,total_xp,total_coin,streak;
    //in_time_limit
    static long streak_game_in_time_limit,base_coin_game_in_time_limit,level_game_in_time_limit;
    static DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    static public DatabaseReference mMessageDatabaseRefrence = rootRef.child("users");
    static public FirebaseAuth mFirebaseAuth;
      FirebaseUser user = mFirebaseAuth.getCurrentUser();
     String var=user.getEmail();



//
    static String user_email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
    static String useremail_id = user_email.replace(".",","); // replaces all dots


    static public void update_all_info_firebase()
    {
        update_over_all_info_firebase();
        update_in_time_limit_info_firebase();

    }
    static public void update_over_all_info_firebase()
    {
        Log.d("get_over_all_info_fire","------------------------iNSIDE IT ----------"+"-----");
        mMessageDatabaseRefrence.child(useremail_id).child("games").child("over_all").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                over_all_datail_class=snapshot.getValue(GameDetailFirebase.class);
                Log.d("get_over_all_info_fire","----------------------------------"+over_all_datail_class.base_coin+over_all_datail_class.level+"-----");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    static public void update_in_time_limit_info_firebase()
    {
        Log.d("update_over_all_info","------------------------iNSIDE IT ----------"+"-----");
        mMessageDatabaseRefrence.child(useremail_id).child("games").child("in_time_limit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                in_time_limit_detail_class=snapshot.getValue(GameDetailFirebase.class);
                Log.d("update_over_all_info","----------------------------------"+in_time_limit_detail_class.base_coin+in_time_limit_detail_class.level+"-----");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
