package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Objects;

public class MessagesActivity extends AppCompatActivity {

    private EditText mesTxtEt;
    private ImageButton sendBtn;
    private RecyclerView recView;
    private TextView userName;
    private ArrayList<MessageHolder> messages;
    private ArrayList<String> users;

    private static final String CURRENT_TEXT = "currentText";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        
        mesTxtEt = findViewById(R.id.messageTxt);
        sendBtn = findViewById(R.id.sendButton);
        recView = findViewById(R.id.RecMessages);
        messages = new ArrayList<>();
        users = new ArrayList<>();


        
        toolBar();



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        CharSequence unsaved = mesTxtEt.getText();
        outState.putCharSequence(CURRENT_TEXT, unsaved);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CharSequence saved = savedInstanceState.getCharSequence(CURRENT_TEXT);
        mesTxtEt.setText(saved);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.settings_menu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem m) {
        if (m.getItemId() == R.id.settings_menu_item) {
            startActivity(new Intent(MessagesActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(m);
    }

    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Messages");
        setSupportActionBar(toolbar);

        //below was getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

}