package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(User.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("iUiSEXjutSsDCe1DdRLFhYFODQxp98xlX6q6t3Qu")
                .clientKey("GzOhz6sdo6mpEXcqar0l3A8em0h4IiUqZ3Rli2l5")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
