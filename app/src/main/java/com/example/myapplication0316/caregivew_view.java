package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class caregivew_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregivew_view);

        Button caregiverNowLocation = findViewById(R.id.CaregiverNowLocation);
        Button caregiverSetLocation = findViewById(R.id.CaregiverSetLocation);
//        Button caregiverSetting = findViewById(R.id.CaregiverSetting);


        caregiverNowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(caregivew_view.this, map.class);
                startActivity(intent);
            }
        });

        caregiverSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(caregivew_view.this, map.class);
                startActivity(intent);
            }
        });
//
//        caregiverSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(caregivew_view.this, caregiver_setting.class);
//                startActivity(intent);
//            }
//        });






    }
}