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

        notices.add("Test notice");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notices);
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences = getApplicationContext()
                .getSharedPreferences("com.example.housem8", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notices", null);

//        if (set == null) {
//            notices.add("Example notice");
//        } else {
//            notices = new ArrayList(set);
//        }



        toolBar();
        addNotice();
        viewNotice();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.settings_menu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem m) {
        if (m.getItemId() == R.id.settings_menu_item) {
            startActivity(new Intent(NoticeboardActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(m);
    }

    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Noticeboard");
        setSupportActionBar(toolbar);

        //below was getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void addNotice(){
        addNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeboardActivity.this, AddNotice.class));
            }
        });
    }

    public void viewNotice(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent intent = new Intent(getApplicationContext(), AddNotice.class);
                intent.putExtra("noticeID", i);
                startActivity(intent);
            }


        });
    }
}