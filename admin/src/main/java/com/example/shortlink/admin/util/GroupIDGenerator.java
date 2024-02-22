package com.example.shortlink.admin.util;


import java.security.SecureRandom;

public class GroupIDGenerator {

    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int length = 6;

    private static final SecureRandom random = new SecureRandom();

    private static StringBuilder sb = new StringBuilder(length);

    public static String generate(){
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        String result = sb.toString();
        sb = new StringBuilder();
        return result;
    }


}
