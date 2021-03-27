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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MessagesActivity extends AppCompatActivity {

    private EditText mesTxtEt;
    private ImageButton sendBtn;
    private RecyclerView recView;
    private TextView housemateNameTextView;
    private ArrayList<Chat> messages;
    private ArrayList<String> housemates;
    private ArrayList<Integer> houseIDs;
    private com.google.firebase.auth.FirebaseUser FirebaseUser;
    private DatabaseReference databaseReference;
    private String uid;
    private String housemateNameString, housemateEmail, houseId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        
        mesTxtEt = findViewById(R.id.messageTxt);
        sendBtn = findViewById(R.id.sendButton);
        recView = findViewById(R.id.RecyclerMessages);
        messages = new ArrayList<>();
        housemates = new ArrayList<>();
        houseIDs = new ArrayList<>();

        housemateNameString = getIntent().getStringExtra("housemate_name");
        housemateEmail = getIntent().getStringExtra("housemate_email");
        housemateNameTextView.setText(housemateNameString);

        //senderName =
        //recipientName =
        FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        uid = FirebaseUser.getUid();


        
        toolBar();



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