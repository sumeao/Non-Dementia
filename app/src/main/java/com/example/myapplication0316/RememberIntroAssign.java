package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class RememberIntroAssign extends AppCompatActivity {
    ImageButton BtnRem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_intro_assign);

        BtnRem = findViewById(R.id.BtnRememberAssign);

        BtnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RememberIntroAssign.this,RememberChoice.class);
                startActivity(intent);
            }
        });
    }
}