package com.example.housem8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private ArrayList<Chat> messages;
    private String nameOfSender;
    private Context context;

    public static final int MSG_TYPE_RIGHT = 0;
    public static final int MSG_TYPE_LEFT = 1;


    public MessageAdapter(ArrayList<Chat> messages, String nameOfSender, Context context) {
        this.messages = messages;
        this.nameOfSender = nameOfSender;
        this.context = context;
    }


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.chat_layout, parent, false);
        return new MessageHolder(view);

//        View view;
//        if (viewType == MSG_TYPE_RIGHT) {
//            view = LayoutInflater.from(context).inflate(R.layout.my_layout, parent, false);
//        } else {
//            view = LayoutInflater.from(context).inflate(R.layout.their_layout, parent, false);
//        }
//        return new MessageHolder(view);
    }

//    @Override
//    public int getItemViewType(int position){
//        if (messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
//            return MSG_TYPE_RIGHT;
//        } else {
//            return MSG_TYPE_LEFT;
//        }
//    }



    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.mSent.setText(messages.get(position).getContent());
//        holder.mReceived.setText(messages.get(position).getContent());
//        holder.name.setText(nameOfSender);

        ConstraintLayout chatLayout = holder.chatLayout;

//        ConstraintLayout myLayout = holder.myLayout;
//        ConstraintLayout theirLayout = holder.theirLayout;


        if (messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
            ConstraintSet cSet = new ConstraintSet();
            cSet.clone(chatLayout);
//            cSet.clear(R.id.name_of_sender, ConstraintSet.LEFT);
            cSet.clear(R.id.my_message, ConstraintSet.LEFT);
//            cSet.connect(R.id.name_of_sender, ConstraintSet.RIGHT, R.id.my_layout, ConstraintSet.RIGHT,0);
            cSet.connect(R.id.my_message, ConstraintSet.RIGHT, R.id.chat_layout, ConstraintSet.LEFT,0);
            cSet.applyTo(chatLayout);
        } else {
            ConstraintSet cSet = new ConstraintSet();
            cSet.clone(chatLayout);
//            cSet.clear(R.id.name_of_sender, ConstraintSet.RIGHT);
            cSet.clear(R.id.my_message, ConstraintSet.RIGHT);
//            cSet.connect(R.id.name_of_sender, ConstraintSet.LEFT, R.id.their_layout, ConstraintSet.LEFT,0);
            cSet.connect(R.id.my_message, ConstraintSet.LEFT, R.id.chat_layout, ConstraintSet.RIGHT,0);
            cSet.applyTo(chatLayout);


        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout myLayout, theirLayout, chatLayout;
        private TextView mSent, mReceived, name;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

//            myLayout = itemView.findViewById(R.id.my_layout);
//            theirLayout = itemView.findViewById(R.id.their_layout);
            chatLayout = itemView.findViewById(R.id.chat_layout);
            mSent = itemView.findViewById(R.id.my_message);
//            mReceived = itemView.findViewById(R.id.their_message);
//            name = itemView.findViewById(R.id.name_of_sender);


        }
    }

}
