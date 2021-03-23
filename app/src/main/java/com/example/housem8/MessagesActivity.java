package com.example.housem8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Message;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.Random;

public class MessagesActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private Button toMainButton;
    private EditText sendMessageText;
    private ImageButton sendMessageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        toMainButton = findViewById(R.id.toMain);
        sendMessageText = findViewById(R.id.enterMessage);
        sendMessageButton = (ImageButton) findViewById(R.id.sendButton);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

//        messageAdapter = new MessageAdapter(this);
//        messagesView = findViewById(R.id.message_list);
//        messagesView.setAdapter(messageAdapter);
//        MemberData data = new MemberData(getRandomName(), getRandomColor());
//
//        scaledrone = new Scaledrone(channelID, data);
//        scaledrone.connect(new Listener() {
//            @Override
//            public void onOpen() {
//                System.out.println("Scaledrone connection open");
//                scaledrone.subscribe(roomName, MessagesActivity.this);
//            }
//
//            @Override
//            public void onOpenFailure(Exception ex) {
//                System.err.println(ex);
//            }
//
//            @Override
//            public void onFailure(Exception ex) {
//                System.err.println(ex);
//            }
//
//            @Override
//            public void onClosed(String reason) {
//                System.err.println(reason);
//            }
//        });

        // Obtain the FirebaseAnalytics instance.




        toolBar();
        toMain();
    }

    public void toolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void toMain(){
        toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessagesActivity.this, MainActivity.class));
            }
        });
    }

//    public void sendMessage(View view) {
//        String message = sendMessageText.getText().toString();
//        if (message.length() > 0) {
//            scaledrone.publish(roomName, message);
//            sendMessageText.getText().clear();
//        }
//    }
//
//    // Successfully connected to Scaledrone room
//    @Override
//    public void onOpen(Room room) {
//        System.out.println("Connected to room");
//    }
//
//    // Connecting to Scaledrone room failed
//    @Override
//    public void onOpenFailure(Room room, Exception ex) {
//        System.err.println(ex);
//    }
//
//    // Received a message from Scaledrone room
//    @Override
//    public void onMessage(Room room, com.scaledrone.lib.Message receivedMessage) {
//        final ObjectMapper mapper = new ObjectMapper();
//        try {
//            final MemberData data = mapper.treeToValue(receivedMessage.getMember().getClientData(), MemberData.class);
//            boolean belongsToCurrentUser = receivedMessage.getClientID().equals(scaledrone.getClientID());
//            final MessagesHandler m = new MessagesHandler(receivedMessage.getData().asText(), data, belongsToCurrentUser);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    messageAdapter.add(m);
//                    messagesView.setSelection(messagesView.getCount() - 1);
//                }
//            });
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String getRandomName() {
//        String[] adjs = {"autumn", "hidden", "bitter", "misty", "silent", "empty", "dry", "dark", "summer", "icy", "delicate", "quiet", "white", "cool", "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy", "weathered", "blue", "billowing", "broken", "cold", "damp", "falling", "frosty", "green", "long", "late", "lingering", "bold", "little", "morning", "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing", "shy", "wandering", "withered", "wild", "black", "young", "holy", "solitary", "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine", "polished", "ancient", "purple", "lively", "nameless"};
//        String[] nouns = {"waterfall", "river", "breeze", "moon", "rain", "wind", "sea", "morning", "snow", "lake", "sunset", "pine", "shadow", "leaf", "dawn", "glitter", "forest", "hill", "cloud", "meadow", "sun", "glade", "bird", "brook", "butterfly", "bush", "dew", "dust", "field", "fire", "flower", "firefly", "feather", "grass", "haze", "mountain", "night", "pond", "darkness", "snowflake", "silence", "sound", "sky", "shape", "surf", "thunder", "violet", "water", "wildflower", "wave", "water", "resonance", "sun", "wood", "dream", "cherry", "tree", "fog", "frost", "voice", "paper", "frog", "smoke", "star"};
//        return (
//                adjs[(int) Math.floor(Math.random() * adjs.length)] +
//                        "_" +
//                        nouns[(int) Math.floor(Math.random() * nouns.length)]
//        );
//    }
//
//    private String getRandomColor() {
//        Random r = new Random();
//        StringBuffer sb = new StringBuffer("#");
//        while(sb.length() < 7){
//            sb.append(Integer.toHexString(r.nextInt()));
//        }
//        return sb.toString().substring(0, 7);
//    }
//}
//
//class MemberData {
//    private String name;
//    private String color;
//
//    public MemberData(String name, String color) {
//        this.name = name;
//        this.color = color;
//    }
//
//    public MemberData() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    @Override
//    public String toString() {
//        return "MemberData{" +
//                "name='" + name + '\'' +
//                ", color='" + color + '\'' +
//                '}';
//    }
}