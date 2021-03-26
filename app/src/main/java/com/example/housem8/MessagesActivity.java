package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.google.firebase.auth.FirebaseUser;
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
    private TextView senderName;
    private TextView recipientName;
    private TextView testText;
    private ArrayList<Chat> messages;
    private ArrayList<String> users;
    private com.google.firebase.auth.FirebaseUser FirebaseUser;
    private DatabaseReference databaseReference;
    private String uid;

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

        testText = findViewById(R.id.sent_by_user);

        //senderName =
        //recipientName =
        FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        uid = FirebaseUser.getUid();


        
        toolBar();
        sendMessage();



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
    
    public void sendMessage(){
        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                testText.setText(mesTxtEt.getText().toString());
                mesTxtEt.setText("");
            }
        });
    }

    public void displayName(){
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProperties userProperties = snapshot.getValue(UserProperties.class);
                if(userProperties != null){
                    String name = userProperties.getName();
                    recipientName.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}