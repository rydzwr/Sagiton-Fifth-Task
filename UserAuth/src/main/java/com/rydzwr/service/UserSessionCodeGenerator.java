package com.rydzwr.service;

import java.util.Random;

public class UserSessionCodeGenerator {
    public static String generate() {
        Random random = new Random();
        int out = random.nextInt();
        if (out < 0) {
            out = out * -1;
        }
        return String.valueOf(out);
    }
}
