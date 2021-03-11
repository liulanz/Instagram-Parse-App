package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.instagram.databinding.ActivityUser2Binding;
import com.parse.ParseUser;

public class UserActivity extends AppCompatActivity {
    ActivityUser2Binding activityUser2Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        activityUser2Binding =  DataBindingUtil.setContentView(this, R.layout.activity_user2);
        activityUser2Binding.ivuser.setImageDrawable(getResources().getDrawable(R.drawable.instagram_user_filled_24));
        activityUser2Binding.ivhome.setImageDrawable(getResources().getDrawable(R.drawable.instagram_home_outline_24));
        activityUser2Binding.ivadd.setImageDrawable(getResources().getDrawable(R.drawable.instagram_new_post_outline_24));

        activityUser2Binding.btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser== null){
                    Intent i = new Intent(UserActivity.this, LoginActivity.class);
                    Toast.makeText(getApplicationContext(), "You Have Successfully Logged Out", Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
            }
        });
        activityUser2Binding.ivhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUser2Binding.ivhome.setImageDrawable(getResources().getDrawable(R.drawable.instagram_home_filled_24));
                activityUser2Binding.ivadd.setImageDrawable(getResources().getDrawable(R.drawable.instagram_new_post_outline_24));
                activityUser2Binding.ivuser.setImageDrawable(getResources().getDrawable(R.drawable.instagram_user_outline_24));
                goHomeActivity();
            }
        });
        activityUser2Binding.ivadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUser2Binding.ivadd.setImageDrawable(getResources().getDrawable(R.drawable.instagram_new_post_filled_24));
                activityUser2Binding.ivhome.setImageDrawable(getResources().getDrawable(R.drawable.instagram_home_outline_24));
                activityUser2Binding.ivuser.setImageDrawable(getResources().getDrawable(R.drawable.instagram_user_outline_24));
                goMainActivity();
            }
        });
    }

    private void goHomeActivity() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}