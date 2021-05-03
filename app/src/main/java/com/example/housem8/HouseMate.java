package com.example.housem8;

import java.util.Random;

/**
 * HouseMate class
 * Author: Maid Rondić (2020)
 * Title: Build Chat App in Android with Java and Firebase
 * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
 * Lesson: 7
 */
public class HouseMate {

    private StringBuilder sb1 = new StringBuilder();
    private StringBuilder sb2 = new StringBuilder();
    private Random random = new Random();

    private String name;
//    private String email;
    private String housemateID;
    private String houseID;
    private String colour;

    /**
     * HouseMate constructor
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 7
     *
     * Empty constructor for HouseMate class
     */
    public HouseMate(){}

    /**
     * HouseMate constructor
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 7
     *
     * Constructor for HouseMate class
     * @param name String value of name
     */
    public HouseMate(String name) {
        this.name = name;
        setHousemateID();
        setHouseID();
        generateColour();
    }

    /**
     * getName() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 7
     *
     * returns value of name
     * @return String value of name
     */
    public String getName() {
        return name;
    }

    /**
     * setName() method
     * Author: Maid Rondić (2020)
     * Title: Build Chat App in Android with Java and Firebase
     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
     * Lesson: 7
     *
     * sets value of name to passed value
     * @param name String value passed to change value of name
     */
    public void setName(String name) {
        this.name = name;
    }

//    /**
//     * getEmail() method
//     * Author: Maid Rondić (2020)
//     * Title: Build Chat App in Android with Java and Firebase
//     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
//     * Lesson: 7
//     *
//     * returns value of email
//     * @return String value of email
//     */
//    public String getEmail() {
//        return email;
//    }
//
//    /**
//     * setEmail() method
//     * Author: Maid Rondić (2020)
//     * Title: Build Chat App in Android with Java and Firebase
//     * Available at: https://www.skillshare.com/classes/Build-Chat-App-in-Android-with-Java-and-Firebase/1043151393/lessons
//     * Lesson: 7
//     *
//     * sets value of email to passed value
//     * @param email String value passed to change value of email
//     */
//    public void setEmail(String email) {
//        this.email = email;
//    }

    /**
     * returns value of houseID
     * @return String value of houseID
     */
    public String getHouseID() {
        return houseID;
    }

    /**
     * returns value of housemateID
     * @return String value of housemateID
     */
    public String getHousemateID() {
        return housemateID;
    }

    /**
     * returns value of colour
     * @return String value of colour
     */
    public String getColour(){
        return colour;
    }

    /**
     * setHouseID() method
     * Author: GeeksForGeeks (2018)
     * Title: Generate random String of given size in Java
     * Available at: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
     *
     * method to get unique random 6 character value and set to houseID
     */
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



    /**
     * setHousemateID() method
     * Author: GeeksForGeeks (2018)
     * Title: Generate random String of given size in Java
     * Available at: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
     *
     * method to get unique random 6 character value and set to housemateID
     */
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

    /**
     * generateColour() method
     * Author: Scaledrone (2018)
     * Title: Android Chat Tutorial: Building A Realtime Messaging App
     * Available at: https://www.scaledrone.com/blog/android-chat-tutorial/
     *
     * method to generate a random colour in the form of a String as a hex value
     */
    public void generateColour() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        colour = sb.toString().substring(0, 7);
    }
}
