package com.example.housem8;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * MessageAdapter class
 * Author: Scaledrone (2018)
 * Title: Android Chat Tutorial: Building A Realtime Messaging App
 * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
 */
public class MessageAdapter extends BaseAdapter {

    List<Message> messages = new ArrayList<Message>();
    Context context;

    /**
     * Constructor for MessageAdapter
     * @param context
     */
    public MessageAdapter(Context context) {
        this.context = context;
    }

    /**
     * add() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * adds Message object to messages ArrayList
     * @param message Message object passed
     */
    public void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    /**
     * method to return number of messages in messages ArrayList
     * @return amount of messages in messages ArrayList
     */
    @Override
    public int getCount() {
        return messages.size();
    }

    /**
     * returns object value at given index
     * @param i index value
     * @return the Message object at index passed
     */
    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    /**
     * returns long value of index passed
     * @param i passed integer index
     * @return long value of index passed
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * getView() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * Changes the view depending on if the user sent or received the message
     */
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        if (message.isBelongsToCurrentUser()) {
            convertView = messageInflater.inflate(R.layout.my_message, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
        } else {
            convertView = messageInflater.inflate(R.layout.their_message, null);
            holder.avatar = (View) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.nameText);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(message.getMemberData().getName());
            holder.messageBody.setText(message.getText());
            GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
            drawable.setColor(Color.parseColor(message.getMemberData().getColour()));
        }

        return convertView;
    }

    /**
     * returns current of registered user
     */
    public String getCurrentName(){
        String name = null;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            name = user.getDisplayName();
        } return name;
    }

}

/**
 * MessageViewHolder class
 * Author: Scaledrone (2018)
 * Title: Android Chat Tutorial: Building A Realtime Messaging App
 * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
 */
class MessageViewHolder {
    public View avatar;
    public TextView name;
    public TextView messageBody;
}