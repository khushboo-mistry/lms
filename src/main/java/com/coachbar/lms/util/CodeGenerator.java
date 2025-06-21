package com.coachbar.lms.util;

import java.util.UUID;

public class CodeGenerator {
    public static String generateCode() {
        int length = 10;
        String code = UUID.randomUUID().toString().substring(0, length).toUpperCase();
        return code.toString();
    }
}

