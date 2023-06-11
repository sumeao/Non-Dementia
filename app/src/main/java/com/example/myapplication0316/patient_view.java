package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class patient_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);

        Button patientnowlocation = findViewById(R.id.PatientNowLocation);
        Button patientsetlocation = findViewById(R.id.PatientSetLocation);
//        Button patientsetting = findViewById(R.id.PatientSetting);

        patientnowlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(patient_view.this, map.class);
                startActivity(intent);
            }
        });

        patientsetlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(patient_view.this, map.class);
                startActivity(intent);
            }
        });

//        patientsetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(patient_view.this, caregivew_view.class);
//                startActivity(intent);
//            }
//        });


    }
}