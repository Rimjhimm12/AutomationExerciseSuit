package com.qa.ae.utils;

public class RandomStringUtil {
    public static String getRandomEmail(){
        String email = "test"+System.currentTimeMillis()+"@gmail.com";
        //String email = "test"+ UUID.randomUUID() +"@gmail.com";
        return email;
    }

    public static String getRandomText(){
        String email = "A6"+System.currentTimeMillis()+"@password";
        //String email = "test"+ UUID.randomUUID() +"@gmail.com";
        return email;
    }
}
