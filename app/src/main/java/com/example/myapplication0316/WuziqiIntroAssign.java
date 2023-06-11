package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WuziqiIntroAssign extends AppCompatActivity {
    ImageButton BtnWuziqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuziqi_intro_assign);

        BtnWuziqi = findViewById(R.id.BtnWuziqiAssign);

        BtnWuziqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WuziqiIntroAssign.this,WuZiQi.class);
                startActivity(intent);
            }
        });
    }
}