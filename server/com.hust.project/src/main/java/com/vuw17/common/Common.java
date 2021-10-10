package com.vuw17.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Common {
    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class.toString());

    public  static String GeneratePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        return password;
    }
}
