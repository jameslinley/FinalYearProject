package com.example.housem8;


/**
 * Message class
 * Author: Scaledrone (2018)
 * Title: Android Chat Tutorial: Building A Realtime Messaging App
 * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
 *
 */
public class Message {
    private String text;
    private HouseMate memberData;
    private boolean belongsToCurrentUser;

    /**
     * Message() constructor
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * Constructor for Message class
     * @param text message content sent by user
     * @param data object of class HouseMate
     * @param belongsToCurrentUser if the message belongs to the user
     */
    public Message(String text, HouseMate data, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberData = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    /**
     * getText() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * returns value of text
     * @return String value of text
     */
    public String getText() {
        return text;
    }

    /**
     * getMemberData() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * returns value of memberData
     * @return HouseMate object of memberData
     */
    public HouseMate getMemberData() {
        return memberData;
    }

    /**
     * isBelongsToCurrentUser() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * returns true or false depending
     * @return boolean
     */
    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
