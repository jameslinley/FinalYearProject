package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Objects;

public class NoticeboardActivity extends AppCompatActivity {

    private ImageView addNoticeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeboard);
        addNoticeBtn = findViewById(R.id.addNotice);

        toolBar();
        addNotice();
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
}