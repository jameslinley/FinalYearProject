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

    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("New Notice");
        setSupportActionBar(toolbar);
    }

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