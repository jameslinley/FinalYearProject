package com.example.housem8;

public class MessageHolder {
    private String user;
    private String altUser;
    private String content;
    
    public MessageHolder() {}

    public MessageHolder(String user, String altUser, String content){
        this.user = user;
        this.altUser = altUser;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAltUser() {
        return altUser;
    }

    public void setAltUser(String altUser) {
        this.altUser = altUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
