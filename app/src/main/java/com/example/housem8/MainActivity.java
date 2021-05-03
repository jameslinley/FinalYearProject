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

    /**
     * adds settings_menu icon as a button in toolbar
     * @param m
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.settings_menu, m);
        return true;
    }

    /**
     * onOptionsItemSelected() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/projects
     * Lesson: 4
     *
     * opens new SettingsActivity activity when pressed on settings_menu icon
     * @param m
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem m) {
        if (m.getItemId() == R.id.settings_menu_item) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(m);
    }

    /**
     * toolBar() method
     * Author@ Coding in Flow (2017)
     * Title: How to Add an Up Button to the AppBar - Android Studio Tutorial
     * Available at: https://www.youtube.com/watch?v=JkVdP-e9BCo&ab_channel=CodinginFlow
     *
     * sets name of current page in toolbar
     */
    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * starts new CalendarActivity activity when calButton is pressed
     */
    public void CalendarMessageSetUp(){
        calButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CalendarActivity.class)));
    }

    /**
     * starts new MessagesActivity activity when mesButton is pressed
     */
    public void MessageButtonSetUp(){
        mesButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MessagesActivity.class)));
    }

    /**
     * starts new NoticeboardActivity activity when notButton is pressed
     */
    public void NoticeboardButtonSetUp(){
        notButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NoticeboardActivity.class)));
    }






}
