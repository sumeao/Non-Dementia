package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Sos extends AppCompatActivity {
    ImageButton BtnPhone,BtnGPS1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        BtnPhone = findViewById(R.id.BtnPhone);
        BtnGPS1 = findViewById(R.id.BtnGPS1);

        BtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse( "tel:"+ "0989909063"));
                startActivity(intent);
            }
        });

        BtnGPS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sos.this,MapPosition.class);
                startActivity(intent);
            }
        });


    }
}