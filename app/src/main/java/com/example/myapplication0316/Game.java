package com.example.myapplication0316;

import static com.example.myapplication0316.R.id.bt2;
import static com.example.myapplication0316.R.id.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageButton button = findViewById(R.id.bt1);
        ImageButton button2 = findViewById(bt2);
        ImageButton button3 = findViewById(bt3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this,TicTacToeIntroAssign.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this,WuziqiIntroAssign.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this,RememberIntroAssign.class);
                startActivity(intent);
            }
        });
    }
}