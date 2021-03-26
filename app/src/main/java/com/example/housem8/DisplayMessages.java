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

import java.util.ArrayList;

public class DisplayMessages extends RecyclerView.Adapter<DisplayMessages.ChatHolder> {

    private ArrayList<Chat> messages;
    private Context context;

    public DisplayMessages(ArrayList<Chat> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public DisplayMessages.ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chat_holder, parent, false);
        return new ChatHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        holder.sentMessage.setText(messages.get(position).getContent());

        ConstraintLayout constraintLayout = holder.chatCL;

        if (messages.get(position).getAltUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.sent_by_user, ConstraintSet.LEFT);
            constraintSet.connect(R.id.sent_by_user, ConstraintSet.RIGHT, R.id.chat_constraintLayout, ConstraintSet.RIGHT, 0);
            constraintSet.applyTo(constraintLayout);
        } else {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.name_of_sender, ConstraintSet.RIGHT);
            constraintSet.clear(R.id.received_by_user, ConstraintSet.RIGHT);
            constraintSet.connect(R.id.received_by_user, ConstraintSet.LEFT, R.id.chat_constraintLayout, ConstraintSet.LEFT, 0);
            constraintSet.connect(R.id.name_of_sender, ConstraintSet.LEFT, R.id.received_by_user, ConstraintSet.TOP, 0);
            constraintSet.applyTo(constraintLayout);
        }

    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder{
        ConstraintLayout chatCL;
        TextView sentMessage;
        TextView receivedMessage;
        TextView senderName;
        public ChatHolder(@NonNull View itemView) {
            super(itemView);

            chatCL = itemView.findViewById(R.id.chat_constraintLayout);
            sentMessage = itemView.findViewById(R.id.sent_by_user);
            receivedMessage = itemView.findViewById(R.id.received_by_user);
            senderName = itemView.findViewById(R.id.name_of_sender);
        }
    }
}
