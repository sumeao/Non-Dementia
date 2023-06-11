package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class InitialPage extends AppCompatActivity {

    ImageView image;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

        image = findViewById(R.id.imageView38);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(1000); // 渐显的时间，单位为毫秒
        image.setVisibility(View.VISIBLE);
        login.setVisibility(View.VISIBLE);
        register.setVisibility(View.VISIBLE);
        image.startAnimation(animation);
        login.startAnimation(animation);
        register.startAnimation(animation);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitialPage.this, login.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitialPage.this, registration.class);
                startActivity(intent);
                finish();
            }
        });

    }
}