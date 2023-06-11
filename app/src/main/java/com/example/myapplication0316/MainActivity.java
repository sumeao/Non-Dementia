package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

//    private SpeechRecognizer mSpeechRecognizer;
//    private Intent mRecognizerIntent;
//    private SpeechRecognizer speechRecognizer;
//    private Intent speechRecognitionIntent;
    private static SQLiteDatabase db;
    private SqlDataBaseHelper sqlDataBaseHelper;
    private byte[] imageData,imageData2;
    Bitmap bitmap;


    ImageButton Btnlogout, BtnEx, BtnGame,  BtnEat, BtnScale, BtnGPS, BtnChatbot;
    ImageView BtnCare,BtnPatient,doublr_arrow;

    String[] bot = {"阿德同學", "阿的同學", "嘿", "喂", "同學"};
    String[] model = {"玩", "遊戲", "做", "檢測", "運動", "吃飯", "提醒", "聊天", "基本資料"};
    Boolean dei = false, tent = false;
    ArrayList<ArrayList<Integer>> unicode = new ArrayList();
    ArrayList<ArrayList<Integer>> botcode = new ArrayList();
    ArrayList<Integer> Ubot = new ArrayList();
    ArrayList<Integer> Uword = new ArrayList();
    TextView caregiverName,patientName;


//    public void setunicode() {
//        for (String str : model) {
//            ArrayList<Integer> unicodes = new ArrayList<>();
//            for (int i = 0; i < str.length(); i++) {
//                unicodes.add(str.codePointAt(i));
//            }
//            unicode.add(unicodes);
//        }
//    }





    @SuppressLint("SetTextI18n")
    public boolean SqlAccountCheck(String account, String selectionArgs) {

        sqlDataBaseHelper = new SqlDataBaseHelper(getApplicationContext());
        db = sqlDataBaseHelper.getReadableDatabase(); // 開啟資料庫

        String test = "";
        String[] test2 = {selectionArgs,"1"};
        test = account+" AND login = ?";

        System.out.println("-----------------------------------------");
        System.out.println(account);
        System.out.println(test);

        Cursor c = db.query("caregiver", null, test, test2, null, null, null);
        c.moveToFirst();


       if(c.getCount() == 0){
           return true;

       }else {
           return false;
       }
    }


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btnlogout = findViewById(R.id.logout);
        BtnEx = findViewById(R.id.BtnEx);
        BtnGame = findViewById(R.id.BtnGame);
        BtnCare = findViewById(R.id.caregiver);

        BtnEat = findViewById(R.id.BtnEat);
        BtnScale = findViewById(R.id.BtnGame2);
        BtnPatient = findViewById(R.id.patient);
        BtnGPS = findViewById(R.id.BtnGPS);
        BtnChatbot = findViewById(R.id.BtnChatbot);
        doublr_arrow =  findViewById(R.id.double_arrow);
        caregiverName = findViewById(R.id.caregiver_name);
        patientName = findViewById(R.id.patient_name);

        // 取得SharedPreference
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        String accountArgs = getPrefs.getString("accountArg", "null");
        String[] selectionArgs = accountArgs.split(",");
        String account = getPrefs.getString("account","account");

        System.out.println("-----------------------------------------");
        System.out.println(accountArgs);

//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
//        speechRecognitionIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        speechRecognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        speechRecognitionIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

//---------------------------------------------------------------------------------------------------
        if(accountArgs == "null"){
            Intent intent = new Intent(MainActivity.this, InitialPage.class);
            startActivity(intent);
            finish();
        }else{
            if (SqlAccountCheck(account, accountArgs)) {
                Intent intent = new Intent(MainActivity.this, InitialPage.class);
                startActivity(intent);
                finish();
            }
        }
//---------------------------------------------------------------------------------------------------

        // 构建查询语句
        String[] projection = {
                "name",
                "photo",
                "patient1"
        };

        String[] projection2 = {
                "name",
                "photo"
        };

        String selection = ""; // 可以使用适当的条件
        String[] selectionArgs2 = new String[0]; // 如果有条件，提供相应的参数
        String sortOrder = ""; // 可以指定排序顺序

        // 执行查询
        Cursor d = db.query(
                "caregiver",
                projection,
                selection,
                selectionArgs2,
                null,
                null,
                sortOrder
        );
        Cursor e = db.query(
                "patient",
                projection2,
                selection,
                selectionArgs2,
                null,
                null,
                sortOrder
        );

        // 遍历查询结果
        if (d.moveToFirst()) {
            String caregiver = d.getString(d.getColumnIndexOrThrow("name"));
            String patient = d.getString(d.getColumnIndexOrThrow("patient1"));
            imageData = d.getBlob(d.getColumnIndexOrThrow("photo"));

            caregiverName.setText("照護者名稱:\n"+caregiver);
//            System.out.println("=================================");
//            System.out.println(imageData);

            if (imageData != null) {
                bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                BtnCare.setImageBitmap(bitmap);
            }
            if(patient != null){
                patientName.setText("病患名稱:\n"+patient);
            }
            if(e.moveToFirst()){
                imageData2 = e.getBlob(d.getColumnIndexOrThrow("photo"));
                if (imageData2 != null) {
                    bitmap = BitmapFactory.decodeByteArray(imageData2, 0, imageData2.length);
                    BtnPatient.setImageBitmap(bitmap);
            }

        }




//        for (String str : bot) {
//            ArrayList<Integer> unicodes = new ArrayList<>();
//            for (int i = 0; i < str.length(); i++) {
//                unicodes.add(str.codePointAt(i));
//            }
//            botcode.add(unicodes);
//        }



        Btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put("login", 0);

                int rowsAffected = db.update("caregiver", values, account, selectionArgs);

                if (rowsAffected > 0) {
                    System.out.println("-------------------------------------");
                    System.out.println(rowsAffected);
                    // 更新成功
                    Toast.makeText(view.getContext(), "登出成功", Toast.LENGTH_SHORT).show();
                    db.close();
                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 更新失败
                    Toast.makeText(view.getContext(), "登出失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        BtnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, newExercise.class);
                startActivity(intent);
            }
        });
        BtnEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Eat.class);
                startActivity(intent);
            }
        });
        BtnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, scale.class);
                startActivity(intent);
            }
        });
        BtnCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, caregiver_setting.class);
                startActivity(intent);
            }
        });
        BtnPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, patient_setting.class);
                startActivity(intent);
            }
        });
        BtnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);
            }
        });
        BtnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, newmap.class);
                startActivity(intent);

            }
        });
        BtnChatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChatBot.class);
                startActivity(intent);
            }
        });

        doublr_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, patient.class);
                startActivity(intent);
            }
        });

//        startSpeechRecognition();


//        speechRecognizer. setRecognitionListener(new RecognitionListener() {
//            @Override
//            public void onReadyForSpeech(Bundle bundle) {
////                startSpeechRecognition();
//            }
//
//            @Override
//            public void onBeginningOfSpeech() {
////                startSpeechRecognition();
//            }
//
//            @Override
//            public void onRmsChanged(float v) {
////                startSpeechRecognition();
//            }
//
//            @Override
//            public void onBufferReceived(byte[] bytes) {
////                startSpeechRecognition();
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//                startSpeechRecognition();
//            }
//
//            @Override
//            public void onError(int i) {
//                startSpeechRecognition();
//            }
//
//            @Override
//            public void onResults(Bundle results) {
//                ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
//
//                Integer Ucount = 0, count = 0;
//
//                for (String str : data) {
//                    for (int i = 0; i < str.length(); i++) {
//                        Uword.add(str.codePointAt(i));
//                    }
//                }
//
//
//                if (!dei) {
//                    for (int i = 0; i < Uword.size(); i++) {
//                        for (int j = 0; j < botcode.size(); j++) {
//                            for (int k = 0; k < botcode.get(j).size(); k++) {
//                                int a1, a2;
//                                a1 = botcode.get(j).get(k);
//                                a2 = Uword.get(i);
//                                if (a1 == a2) {
//                                    Ucount++;
//                                    if (Ucount == botcode.get(j).size()) {
//                                        Toast.makeText(
//                                                getApplicationContext(), "您好! 請問有甚麼事嗎?", Toast.LENGTH_SHORT
//                                        ).show();
//                                        dei = true;
//                                        setunicode();
//
//                                        break;
//                                    }
//                                    break;
//                                }
//
//                            }
//                        }
//                    }
//                    Uword.removeAll(Uword);
//                    startSpeechRecognition();
////                    System.out.println("---------------------------------------");
////                    System.out.println(Uword);
//                    Uword.removeAll(Uword);
//                } else {
//                    int a1, a2;
//                    for (int i = 0; i < unicode.size(); i++) {
//                        for (int j = 0; j < Uword.size(); j++) {
//                            for (int k = 0; k < unicode.get(i).size(); k++) {
//                                a1 = unicode.get(i).get(k);
//                                a2 = Uword.get(j);
//                                if (a1 == a2) {
//                                    count++;
//                                    if (count == unicode.get(i).size()) {
//                                        switch (i) {
//                                            case 0:
//                                            case 1:
//                                                tent = true;
//                                                Intent intent0 = new Intent(MainActivity.this, Game.class);
//                                                startActivity(intent0);
//                                                Uword.removeAll(Uword);
//                                                break;
//                                            case 2:
//                                            case 3:
//                                                tent = true;
//                                                Intent intent1 = new Intent(MainActivity.this, scale.class);
//                                                startActivity(intent1);
//                                                Uword.removeAll(Uword);
//                                                break;
//                                            case 4:
//                                                tent = true;
//                                                Intent intent2 = new Intent(MainActivity.this, Exercise.class);
//                                                startActivity(intent2);
//                                                Uword.removeAll(Uword);
//                                                break;
//                                            case 5:
//                                                tent = true;
//                                                Intent intent3 = new Intent(MainActivity.this, Eat.class);
//                                                startActivity(intent3);
//                                                Uword.removeAll(Uword);
//                                                break;
//                                            case 6:
//                                                tent = true;
//                                                Intent intent4 = new Intent(MainActivity.this, MapPosition.class);
//                                                startActivity(intent4);
//                                                Uword.removeAll(Uword);
//                                                break;
//                                            case 7:
//                                                tent = true;
//                                                Intent intent5 = new Intent(MainActivity.this, ChatBot.class);
//                                                startActivity(intent5);
//                                                Uword.removeAll(Uword);
//                                                break;
//                                            case 8:
//                                                tent = true;
//                                                Intent intent6 = new Intent(MainActivity.this, Setting.class);
//                                                startActivity(intent6);
//                                                Uword.removeAll(Uword);
//                                                break;
//
//                                        }
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    Uword.removeAll(Uword);
//                }
//            }
//
//            @Override
//            public void onPartialResults(Bundle bundle) {
////                startSpeechRecognition();
//            }
//
//            @Override
//            public void onEvent(int i, Bundle bundle) {
////                startSpeechRecognition();
//            }
//        });
    }

//    private void startSpeechRecognition() {
//        if (!tent) {
//            speechRecognizer.startListening(speechRecognitionIntent);
//        }
//    }
}}
