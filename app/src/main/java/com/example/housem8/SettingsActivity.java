package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private Button logoutBtn;
    private TextView dname, houseIDTxt, housemateIDTxt;
    private FirebaseUser FirebaseUser;
    private DatabaseReference dbref;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        logoutBtn = findViewById(R.id.logout_button);
        dname = findViewById(R.id.settingsName);
        houseIDTxt = findViewById(R.id.houseIDTxt);
        housemateIDTxt = findViewById(R.id.housemateIDTxt);
        FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        dbref = FirebaseDatabase.getInstance().getReference("user");
        uid = FirebaseUser.getUid();

        toolBar();
        logOut();
        displayName();
        setHouseID();
        setHousemateID();
    }

    /**
     * toolBar() method
     * Author@ Coding in Flow (2017)
     * Title: How to Add an Up Button to the AppBar - Android Studio Tutorial
     * Available at: https://www.youtube.com/watch?v=JkVdP-e9BCo&ab_channel=CodinginFlow
     *
     * sets name of current page in toolbar and a back button to previous page
     */
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Settings");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * logs user out when logoutBtn is pressed and starts new LogInActivity activity
     */
    public void logOut() {
        logoutBtn.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(SettingsActivity.this, LogInActivity.class));
        });
    }

    /**
     * displayName() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 7
     *
     * sets dname to name of current user
     */
    public void displayName(){
        dbref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HouseMate houseMate = snapshot.getValue(HouseMate.class);
                if(houseMate != null){
                    String name = houseMate.getName();
                    dname.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * sets houseIDTxt to housemateID of current user
     */
    public void setHouseID(){
        dbref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HouseMate houseMate = snapshot.getValue(HouseMate.class);
                if(houseMate != null){
                    String s = houseMate.getHouseID();
                    houseIDTxt.setText(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * sets housemateIDTxt to houseID of current user
     */
    public void setHousemateID(){
        dbref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HouseMate houseMate = snapshot.getValue(HouseMate.class);
                if(houseMate != null){
                    String s = houseMate.getHousemateID();
                    housemateIDTxt.setText(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}