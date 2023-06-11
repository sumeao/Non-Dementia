package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {

    private SQLiteDatabase db1, db2;
    private String login_account, login_password;
    EditText edit_account, edit_password;
    Button submit;
    TextView reregisterNow;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SqlDataBaseHelper dbHelper = new SqlDataBaseHelper(getApplicationContext());
        db1 = dbHelper.getWritableDatabase();
        db2 = dbHelper.getReadableDatabase();

        edit_account = findViewById(R.id.account);
        edit_password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        reregisterNow = findViewById(R.id.registerNow);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_account = edit_account.getText().toString();
                login_password = edit_password.getText().toString();


                String selection = "account = ?";
                String[] selectionArgs = new String[1];
                selectionArgs[0] = login_account;


                if (checkCredentials(login_account, login_password)) {
                    ContentValues values = new ContentValues();
                    values.put("login", 1);

                    int rowsAffected = db1.update("caregiver", values, selection, selectionArgs);

                    if (rowsAffected > 0) {
                        // 更新成功
                        Toast.makeText(view.getContext(), "登錄成功", Toast.LENGTH_SHORT).show();
                        db1.close();

                        // 取得SharedPreference
                        SharedPreferences getPrefs = PreferenceManager
                                .getDefaultSharedPreferences(getBaseContext());
                        // 取得Editor
                        SharedPreferences.Editor editor = getPrefs.edit();
                        // 將version的值設為1
                        editor.putString("account", selection);
                        editor.putString("accountArg", selectionArgs[0]);
                        System.out.println("-----------------------------");
                        System.out.println(selection);
                        System.out.println(selectionArgs[0]);
                        // 套用變更，一定要apply才會生效
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        // 更新失败
                        Toast.makeText(view.getContext(), "登錄失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "帳號或密碼有誤，請重新輸入!!!", Toast.LENGTH_LONG).show();
                }

            }
        });

        reregisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), registration.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean checkCredentials(String credential, String password) {
        String selection = "(account=? OR phone=? OR email=?)";
        String[] selectionArgs = {credential, credential, credential};
//
//        Cursor cursor = db2.query("users", null, selection, selectionArgs, null, null, null);
        Cursor cursor = db2.query("caregiver", null, selection, selectionArgs, null, null, null);
        boolean exists = false;

        while (cursor.moveToNext()) {
            String storedPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            if (password.equals(storedPassword)) {
                exists = true;
                break;
            }
        }

        cursor.close();
        return exists;
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        db2.close();
//    }
}
