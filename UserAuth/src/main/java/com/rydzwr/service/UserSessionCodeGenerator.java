package com.rydzwr.service;

import java.util.UUID;

public class UserSessionCodeGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
