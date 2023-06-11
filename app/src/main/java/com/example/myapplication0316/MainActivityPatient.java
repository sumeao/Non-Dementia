package com.example.myapplication0316;

import static com.example.myapplication0316.R.id.BtnExPat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivityPatient extends AppCompatActivity {

    ImageButton BtnExPat,BtnSchedule;
    Button BtnCaregiver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);

        BtnExPat = findViewById(R.id.BtnExPat);
        BtnCaregiver = findViewById(R.id.BtnCaregiver);
        BtnSchedule = findViewById(R.id.BtnSchedule);
        BtnExPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityPatient.this,ExercisePatient.class);
                startActivity(intent);
            }
        });
        BtnCaregiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityPatient.this,MainActivity.class);
                startActivity(intent);
            }
        });

        BtnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://calendar.google.com/calendar/u/0?cid=NWQ0OWRiZDQxYjZhYWYwYmNlOTNkMDI1YzM5ZjE3ZTZlOTBiMjM5NmM1OTVjZGM3ZTIwMzdhMDkxZGI0ZjFlN0Bncm91cC5jYWxlbmRhci5nb29nbGUuY29t");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
}