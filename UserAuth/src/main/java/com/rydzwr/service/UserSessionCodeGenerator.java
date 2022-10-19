package com.rydzwr.service;

import java.util.Random;
import java.util.UUID;

public class UserSessionCodeGenerator {
    public static String generate() {
        //return UUID.randomUUID().toString();
        //return String.valueOf(Math.random());
        Random random = new Random();
        int out = random.nextInt();
        if (out < 0) {
            out = out * -1;
        }
        return String.valueOf(out);
    }
}
