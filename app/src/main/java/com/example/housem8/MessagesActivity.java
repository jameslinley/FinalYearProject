package com.example.housem8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.Objects;

/**
 * MessagesActivity class
 * Author: Scaledrone (2018)
 * Title: Android Chat Tutorial: Building A Realtime Messaging App
 * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
 *
 */
public class MessagesActivity extends AppCompatActivity implements RoomListener{

    private String housemateName, housemateEmail;
    private String name;

    private String channelID = "JVPx7XpHmmkhj8Lp";
    private String roomName = "observable-room";
    private EditText messageInput;
    private Scaledrone scaledrone;
    private MessageAdapter messageAdapter;
    private ListView messagesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        messageInput = (EditText) findViewById(R.id.editText);
        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        HouseMate housemate = new HouseMate("Name");

        scaledrone = new Scaledrone(channelID, housemate);
        connectToScaledrone();

        toolBar();
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
            startActivity(new Intent(MessagesActivity.this, SettingsActivity.class));
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
        toolbar.setTitle("Messages");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * connectToScaledrone() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * method to connect to a set Scaledrone room
     */
    public void connectToScaledrone(){
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                scaledrone.subscribe(roomName, MessagesActivity.this);
            }

            @Override
            public void onOpenFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onClosed(String reason) {
                System.err.println(reason);
            }
        });
    }

    /**
     * sendMessage() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * method to publish messageInput to Scaledrone server and room
     * @param view
     */
    public void sendMessage(View view) {
        String message = messageInput.getText().toString();
        if (message.length() > 0) {
            scaledrone.publish("observable-room", message);
            messageInput.getText().clear();
        }
    }

    /**
     * onOpen() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * method to be called when connection is established
     * @param room Room passed value
     */
    @Override
    public void onOpen(Room room) {
        System.out.println("Connected to room");
    }

    /**
     * onOpenFailure() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * method to be called when connection is failed
     * @param room Room passed value
     * @param ex Exception passed
     */
    @Override
    public void onOpenFailure(Room room, Exception ex) {
        System.err.println(ex);
    }

    /**
     * onOpenFailure() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * method to display a received message from other user
     * @param room
     * @param receivedMessage
     */
    @Override
    public void onMessage(Room room, com.scaledrone.lib.Message receivedMessage) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final HouseMate data = mapper.treeToValue(receivedMessage.getMember().getClientData(), HouseMate.class);
            boolean belongsToCurrentUser = receivedMessage.getClientID().equals(scaledrone.getClientID());
            final Message message = new Message(receivedMessage.getData().asText(), data, belongsToCurrentUser);
            runOnUiThread(() -> {
                messageAdapter.add(message);
                messagesView.setSelection(messagesView.getCount() - 1);
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}