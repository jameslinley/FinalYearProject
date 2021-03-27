package com.example.housem8;

import java.util.Random;

public class HouseMate {

    private StringBuilder sb = new StringBuilder();
    private Random random = new Random();


    private String name;
    private String email;
    private String houseID;

    public HouseMate(){}

    public HouseMate(String name, String email) {
        this.name = name;
        this.email = email;
        setHouseID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbs = "0123456789";
        String allChars = upperCase + lowerCase + numbs;
        int length = 6;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(allChars.length());

            char randomChars = allChars.charAt(index);
            sb.append(randomChars);
        }
        houseID = sb.toString();
    }

}
