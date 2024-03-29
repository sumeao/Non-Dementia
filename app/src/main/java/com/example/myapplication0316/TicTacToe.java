package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TicTacToe extends AppCompatActivity {

    private Button btn_2player , btn_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        btn_2player = findViewById(R.id.btn_2player);
        btn_robot = findViewById(R.id.btn_robot);

        btn_2player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TicTacToe.this,TicTwoPlayer.class));
            }
        });

        btn_robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TicTacToe.this,TicOnePlayer.class));
            }
        });
    }
}