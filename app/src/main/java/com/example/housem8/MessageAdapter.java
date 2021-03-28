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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private ArrayList<Chat> messages;
    private String nameOfSender;
    private Context context;

    public MessageAdapter(ArrayList<Chat> messages, String nameOfSender, Context context) {
        this.messages = messages;
        this.nameOfSender = nameOfSender;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_holder, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.mSent.setText(messages.get(position).getContent());

        ConstraintLayout layout = holder.ccll;

        if (messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
            ConstraintSet cSet = new ConstraintSet();
            cSet.clone(layout);
            cSet.clear(R.id.sent_message, ConstraintSet.LEFT);
            cSet.connect(R.id.sent_message, ConstraintSet.RIGHT, R.id.ccLayout, ConstraintSet.RIGHT, 5);
            cSet.applyTo(layout);
        } else {
            ConstraintSet cSet = new ConstraintSet();
            cSet.clone(layout);
            cSet.clear(R.id.sent_message, ConstraintSet.RIGHT);
            cSet.connect(R.id.sent_message, ConstraintSet.LEFT, R.id.ccLayout, ConstraintSet.LEFT, 5);
            cSet.applyTo(layout);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder{
        ConstraintLayout ccll;
        TextView mSent, mReceived, name;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            ccll = itemView.findViewById(R.id.ccLayout);
            mSent = itemView.findViewById(R.id.sent_message);
            mReceived = itemView.findViewById(R.id.received_message);
            name = itemView.findViewById(R.id.name_of_sender);
        }
    }

}
