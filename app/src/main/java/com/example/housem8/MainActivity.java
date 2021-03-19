package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton calButton;
    private ImageButton mesButton;
    private ImageButton notButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        calButton = findViewById(R.id.calButton);
        mesButton = findViewById(R.id.mesButton);
        notButton = findViewById(R.id.notButton);
//        noticeboard = new Noticeboard();

        toolBar();
        toCalendar();

    }

    //method to show toolbar on each page
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void toCalendar(){
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });
    }

    //method to change to fragment once button is clicked
//    public void calFrag(){
//        Calendar cal = new Calendar(); //may need to move this
//
//        calButton.setOnClickListener(v -> {
//            calButton.setVisibility(View.GONE);
//            mesButton.setVisibility(View.GONE);
//            notButton.setVisibility(View.GONE);
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().add(R.id.mainLayout, cal).commit();
//        });
//    }

    public void mesFrag(){
        Messages mes = new Messages(); //may need to move this

        mesButton.setOnClickListener(v -> {
            calButton.setVisibility(View.GONE);
            mesButton.setVisibility(View.GONE);
            notButton.setVisibility(View.GONE);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.mainLayout, mes).commit();
        });
    }

    public void notFrag(){
        Noticeboard noticeboard = new Noticeboard(); //may need to move this

        notButton.setOnClickListener(v -> {
            calButton.setVisibility(View.GONE);
            mesButton.setVisibility(View.GONE);
            notButton.setVisibility(View.GONE);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.mainLayout, noticeboard).commit();
        });
    }



}
