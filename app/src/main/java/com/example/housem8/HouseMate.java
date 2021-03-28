package com.example.housem8;

import java.util.Random;

public class HouseMate {

    private StringBuilder sb1 = new StringBuilder();
    private StringBuilder sb2 = new StringBuilder();
    private Random random = new Random();


    private String name;
    private String email;
    private String housemateID;
    private String houseID;

    public HouseMate(){}

    public HouseMate(String name, String email) {
        this.name = name;
        this.email = email;
        setHousemateID();
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
            sb1.append(randomChars);
        }
        houseID = sb1.toString();
    }

    public String getHousemateID() {
        return housemateID;
    }

    public void setHousemateID() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbs = "0123456789";
        String allChars = upperCase + lowerCase + numbs;
        int length = 6;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(allChars.length());

            char randomChars = allChars.charAt(index);
            sb2.append(randomChars);
        }
        housemateID = sb2.toString();
    }

}
