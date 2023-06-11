package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WuZiQi extends AppCompatActivity {
    Button letgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wu_zi_qi);
        letgo = findViewById(R.id.letgo);

        letgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(WuZiQi.this, wuziqifinal.class);
                startActivity(intent);
            }
        });
    }
}