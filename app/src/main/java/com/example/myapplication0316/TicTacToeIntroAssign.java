package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TicTacToeIntroAssign extends AppCompatActivity {
    ImageButton BtnTic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_intro_assign);
        BtnTic = findViewById(R.id.BtnTTTAssign);
        BtnTic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicTacToeIntroAssign.this,TicTacToe.class);
                startActivity(intent);
            }
        });
    }
}