package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Setting extends AppCompatActivity {
    ArrayList<Integer> unicode = new ArrayList();
    ArrayList<Integer> Uword = new ArrayList();
    String[] back = {"上一頁"};
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognitionIntent;
    ImageButton save;
    private EditText uname,uphone,uaddress,udisease;
    private SharedPreferences mSharedPreferences;

    private static final String EDIT_TEXT_CONTENTS_KEY = "EditTextContents";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText editText = findViewById(R.id.editTextTextPersonName);
        outState.putString(EDIT_TEXT_CONTENTS_KEY, editText.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        save = findViewById(R.id.save);
        uname = findViewById(R.id.editTextTextPersonName);
        uphone = findViewById(R.id.editTextTextPersonNumber);
        uaddress = findViewById(R.id.editTextTextPersonAddress);
        udisease = findViewById(R.id.editTextTextPersonDisease1);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognitionIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());



        for (String str : back) {
            for (int i = 0; i < str.length(); i++) {
                unicode.add(str.codePointAt(i));
            }
        }

       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String name = mSharedPreferences.getString("name", "default_name");
               String phone = mSharedPreferences.getString("phone", "default_phone");
               String address = mSharedPreferences.getString("address", "default_address");
               String disease = mSharedPreferences.getString("disease", "default_disease");

               uname.setText(name);
               uphone.setText(phone);
               uaddress.setText(address);
               udisease.setText(disease);

               Toast.makeText(
                       getApplicationContext(), "儲存成功!!!", Toast.LENGTH_SHORT
               ).show();
           }
       });




        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {
                speechRecognizer.startListening(speechRecognitionIntent);
            }

            @Override
            public void onResults(Bundle results) {
                int a1, a2, count = 0;
                ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);

                for (String str : data) {
                    for (int i = 0; i < str.length(); i++) {
                        Uword.add(str.codePointAt(i));
                    }
                }

                for (int i = 0; i < unicode.size(); i++) {
                    for (int j = 0; j < Uword.size(); j++) {

                        a1 = unicode.get(i);
                        a2 = Uword.get(j);
                        if (a1 == a2) {
                            count++;
                            if (count == unicode.size()) {
                                Intent intent = new Intent(Setting.this, MainActivity.class);
                                startActivity(intent);
                            }
                            break;
                        }
                    }

                }
                Uword.removeAll(Uword);
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("name", uname.getText().toString());
        editor.putString("phone", uphone.getText().toString());
        editor.putString("address", uaddress.getText().toString());
        editor.putString("disease", udisease.getText().toString());
        editor.apply();
    }
}
