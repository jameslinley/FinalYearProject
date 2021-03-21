package com.example.housem8;

public class MessagesHandler {

    private String text;
    private MemberData memberData;
    private boolean belongsToCurrentUser;

    public MessagesHandler(String text, MemberData data, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberData = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return text;
    }

    public MemberData getMemberData() {
        return memberData;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }



}
