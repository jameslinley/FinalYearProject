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
        toolBar();

        calButton = findViewById(R.id.calButton);
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCalendarActivity();
            }
        });

        mesButton = findViewById(R.id.mesButton);
        mesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMessagesActivity();
            }
        });

        notButton = findViewById(R.id.notButton);
        notButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNoticeboardActivity();
            }
        });
    }

    //method to show toolbar on each page
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void toCalendarActivity(){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void toMessagesActivity(){
        Intent intent = new Intent(this, MessagesActivity.class);
        startActivity(intent);
    }

    public void toNoticeboardActivity(){
        Intent intent = new Intent(this, NoticeboardActivity.class);
        startActivity(intent);
    }


//    public void toNoticeboard(){
//        notButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, NoticeboardActivity.class));
//            }
//        });
//    }



}
