package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    private EditText messageInput;
    private ImageButton sendBtn;
    private RecyclerView recyclerView;
    private ArrayList<Chat> messages;
    private String housemateName, housemateEmail, housemateHouseID, houseID;
    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        housemateName = getIntent().getStringExtra("housemate_name");
        housemateEmail = getIntent().getStringExtra("housemate_email");
        housemateHouseID = getIntent().getStringExtra("housemate_houseID");

        recyclerView = findViewById(R.id.RecyclerMessages);
        messageInput = findViewById(R.id.messageTxt);
        sendBtn = findViewById(R.id.sendButton);


        messages = new ArrayList<>();

        messageAdapter = new MessageAdapter(messages, getIntent().getStringExtra("housemate_name"), MessagesActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);


        toolBar();
        setupMessages();
        sendButton();

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
        toolbar.setTitle(housemateName);
        setSupportActionBar(toolbar);
        //below was getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void setupMessages(){
        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String hid = snapshot.getValue(HouseMate.class).getHouseID();
                houseID = hid + housemateHouseID;
                messageListener(houseID);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void messageListener(String houseID){
        FirebaseDatabase.getInstance().getReference("messages/" + houseID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for (DataSnapshot d : snapshot.getChildren()){
                    messages.add(d.getValue(Chat.class));
                }
                messageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messages.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void sendButton(){
        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("messages/" + houseID).push().setValue(new Chat(FirebaseAuth.getInstance().getCurrentUser().getEmail(),housemateEmail, messageInput.getText().toString()));
                messageInput.setText("");
            }
        });
    }


}