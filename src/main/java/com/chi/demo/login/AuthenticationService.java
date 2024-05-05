package com.chi.demo.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password) {
        // Authentication
        // name - chi
        // pwd - hithere
        boolean isValidUsername = username.equalsIgnoreCase("chi");
        boolean isValidPassword = password.equalsIgnoreCase("hithere");

        return isValidUsername && isValidPassword;
    }
}
