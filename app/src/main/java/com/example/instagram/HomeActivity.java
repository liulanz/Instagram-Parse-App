package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.instagram.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding activityHomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activityHomeBinding =  DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.ivhome.setImageDrawable(getResources().getDrawable(R.drawable.instagram_home_filled_24));
        activityHomeBinding.ivadd.setImageDrawable(getResources().getDrawable(R.drawable.instagram_new_post_outline_24));
        activityHomeBinding.ivuser.setImageDrawable(getResources().getDrawable(R.drawable.instagram_user_outline_24));
        activityHomeBinding.ivuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityHomeBinding.ivuser.setImageDrawable(getResources().getDrawable(R.drawable.instagram_user_filled_24));
                activityHomeBinding.ivhome.setImageDrawable(getResources().getDrawable(R.drawable.instagram_home_outline_24));
                activityHomeBinding.ivadd.setImageDrawable(getResources().getDrawable(R.drawable.instagram_new_post_outline_24));
                goUserActivity();
            }
        });

        activityHomeBinding.ivadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityHomeBinding.ivadd.setImageDrawable(getResources().getDrawable(R.drawable.instagram_new_post_filled_24));
                activityHomeBinding.ivhome.setImageDrawable(getResources().getDrawable(R.drawable.instagram_home_outline_24));
                activityHomeBinding.ivuser.setImageDrawable(getResources().getDrawable(R.drawable.instagram_user_outline_24));
                goMainActivity();
            }
        });
    }

    private void goUserActivity() {
        Intent i = new Intent(this, UserActivity.class);
        startActivity(i);
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}