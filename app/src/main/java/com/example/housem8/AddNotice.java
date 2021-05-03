package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Objects;

public class AddNotice extends AppCompatActivity {
    
    private TextView title,notice;
    private Button save;
    private int noticeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        
        title = findViewById(R.id.notice_title);
        notice = findViewById(R.id.notice_input);
        save = findViewById(R.id.save_notice);

        toolBar();
        noticeIDMethod();
        saveNoticeText();
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
        toolbar.setTitle("New Notice");
        setSupportActionBar(toolbar);
    }

    /**
     * noticeIDMethod() method
     * Author: Developer PaniBus (2018)
     * Title: Build a Note App with Android Studio, Java and Permanent Storage
     * Available at: https://www.youtube.com/watch?v=48EB4HeP1kI&ab_channel=DeveloperPaniBus
     *
     * displays title of notice and saves to HashSet
     */
    public void noticeIDMethod(){
        Intent intent = getIntent();
        noticeID = intent.getIntExtra("noticeID", -1);

        if (noticeID != -1) {
            title.setText(NoticeboardActivity.notices.get(noticeID));
        } else {
            NoticeboardActivity.notices.add("");
            noticeID = NoticeboardActivity.notices.size() - 1;
            NoticeboardActivity.adapter.notifyDataSetChanged();

            SharedPreferences sharedPreferences = getApplicationContext()
                    .getSharedPreferences("com.example.housem8", Context.MODE_PRIVATE);

            HashSet<String> set = new HashSet(NoticeboardActivity.notices);
            sharedPreferences.edit().putStringSet("notices", set).apply();
        }
    }

    /**
     * saveNoticeText method
     * Author: Developer PaniBus (2018)
     * Title: Build a Note App with Android Studio, Java and Permanent Storage
     * Available at: https://www.youtube.com/watch?v=48EB4HeP1kI&ab_channel=DeveloperPaniBus
     *
     * saves title value to notices ArrayList in NoticeboardActivity class saves
     */
    public void saveNoticeText(){
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NoticeboardActivity.notices.set(noticeID, String.valueOf(s));
                NoticeboardActivity.adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}