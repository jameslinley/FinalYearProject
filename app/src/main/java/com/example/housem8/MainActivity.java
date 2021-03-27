package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        toolBar();
        CalendarMessageSetUp();
        MessageButtonSetUp();
        NoticeboardButtonSetUp();

    }

    //method to show toolbar on each page
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //methods to navigate to certain Activities
    public void CalendarMessageSetUp(){
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });
    }

    public void MessageButtonSetUp(){
        mesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HouseMateActivity.class));
            }
        });
    }

    public void NoticeboardButtonSetUp(){
        notButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoticeboardActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.settings_menu, m);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem m) {
        if (m.getItemId() == R.id.settings_menu_item) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(m);
    }




}
