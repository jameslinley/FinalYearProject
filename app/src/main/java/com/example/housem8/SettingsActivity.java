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
import java.util.Random;

public class SettingsActivity extends AppCompatActivity {

    private Button logoutBtn;
    private TextView dname, houseIDTxt;
    private FirebaseUser FirebaseUser;
    private DatabaseReference databaseReference;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        logoutBtn = findViewById(R.id.logout_button);
        dname = findViewById(R.id.test);
        houseIDTxt = findViewById(R.id.houseIDTxt);
        FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        uid = FirebaseUser.getUid();

        toolBar();
        logOut();
        displayName();
        setHouseID();
    }
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Settings");

        //below was getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void logOut(){
        logoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SettingsActivity.this, LogInActivity.class));
            }
        });
    }

    public void displayName(){
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void setHouseID(){
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HouseMate houseMate = snapshot.getValue(HouseMate.class);
                if(houseMate != null){
                    String hID = houseMate.getHouseID();
                    houseIDTxt.setText(hID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}