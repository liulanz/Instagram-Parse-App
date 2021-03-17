package com.example.instagram;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagram.MainActivity;
import com.example.instagram.R;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton ;
    ProgressBar loadingProgressBar;
    Button signupButton ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            goMainActivity();
            return;
            // do stuff with the user
        }
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btnlogin);
        loadingProgressBar = findViewById(R.id.loading);
        signupButton = findViewById(R.id.btnsignup);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginUser(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(LoginActivity.this, "username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveUser(username, password);
            }
        });
    }

    private void saveUser(String username, String password) {
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if(e!=null){
                    Log.e("LoginActivity","Issue with saving new user", e);
                    Toast.makeText(LoginActivity.this, "Error while saving", Toast.LENGTH_SHORT).show();
                    return;
                }
                loadingProgressBar.setVisibility(View.INVISIBLE);
                Log.i("LoginActivity","Save successfully!");
                Toast.makeText(LoginActivity.this, "You have successfully signed up. PlEASE Login", Toast.LENGTH_SHORT).show();
                usernameEditText.setText("");
                passwordEditText.setText("");
            }
        });


    }

    private void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if (e!=null) {
                    Log.e("LOGINVIEWMODEL", "Issue with login: ", e);
                    Toast.makeText(getApplicationContext(), "Issue with login", Toast.LENGTH_SHORT).show();
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    goMainActivity();
                }
                return;


            }


        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}