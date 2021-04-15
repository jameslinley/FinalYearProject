package com.example.housem8;

public class Message {
    private String text;
    private HouseMate memberData;
    private boolean belongsToCurrentUser;

    public Message(String text, HouseMate data, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberData = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return text;
    }

    public HouseMate getMemberData() {
        return memberData;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
