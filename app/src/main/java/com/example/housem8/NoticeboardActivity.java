package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class NoticeboardActivity extends AppCompatActivity {

    private ImageView addNoticeBtn;
    private ListView listView;
    static ArrayList<String> notices;
    static ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeboard);
        addNoticeBtn = findViewById(R.id.addNotice);
        listView = findViewById(R.id.nbListView);
        notices = new ArrayList<>();
//        notices.add("Test notice");

        preferencesSetUp();
        toolBar();
        addNotice();
        viewNotice();
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
            startActivity(new Intent(NoticeboardActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(m);
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
        toolbar.setTitle("Noticeboard");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * starts new addNotice activity when addNoticeBtn is pressed
     */
    public void addNotice(){
        addNoticeBtn.setOnClickListener(v ->
                startActivity(new Intent(NoticeboardActivity.this, AddNotice.class)));
    }

    /**
     * preferencesSetUp() method
     * Author: Developer PaniBus (2018)
     * Title: Build a Note App with Android Studio, Java and Permanent Storage
     * Available at: https://www.youtube.com/watch?v=48EB4HeP1kI&ab_channel=DeveloperPaniBus
     *
     * saves values in ListView and adds to notice ArrayList
     */
    public void preferencesSetUp(){
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notices);
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences("com.example.housem8", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notices", null);

    }

    /**
     * viewNotice() method
     * Author: Developer PaniBus (2018)
     * Title: Build a Note App with Android Studio, Java and Permanent Storage
     * Available at: https://www.youtube.com/watch?v=48EB4HeP1kI&ab_channel=DeveloperPaniBus
     *
     * method to open AddNotice activity when pressed on listView item
     */
    public void viewNotice(){
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), AddNotice.class);
            intent.putExtra("noticeID", i);
            startActivity(intent);
        });
    }
}