package com.example.instagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    public void setUsername(String username){
        put(USERNAME, username);
    }

    public  void setPassword(String password){
        put(PASSWORD, password);
    }
}

