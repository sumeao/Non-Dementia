package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ScaleDir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_dir);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton Btback = findViewById(R.id.BtnRememberAssign);

        Btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScaleDir.this,scale.class);
                startActivity(intent);
            }
        });
    }
}