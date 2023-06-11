package com.example.myapplication0316;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registration extends Activity {

    private EditText edit_Account, edit_Password, edit_Name, edit_Email, edit_Phone;
    private String edit_Account_Text, edit_Password_Text, edit_Email_Text, edit_Phone_Text, edit_name_Text;
    private Button btn_Insert;
    private SqlDataBaseHelper dbHelper;
    private SQLiteDatabase db;
    TextView loginNow;
    Integer login = 0;


    public boolean isTableExists(String databasePath, String tableName) {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
            boolean tableExists = (cursor != null) && (cursor.getCount() > 0);
            if (cursor != null) {
                cursor.close();
            }
            return tableExists;
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return false;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        String databasePath = getApplicationContext().getDatabasePath("users.db").getPath();
//        SQLiteDatabase db1 = null;


        edit_Account = findViewById(R.id.account);
        edit_Password = findViewById(R.id.password);
        edit_Email = findViewById(R.id.mail);
        edit_Phone = findViewById(R.id.phone);
        edit_Name = findViewById(R.id.name);
        btn_Insert = findViewById(R.id.submit);
        loginNow = findViewById(R.id.loginNow);

//        try {
//            db1 = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
//        } catch (SQLiteException e) {
//            // 数据库不存在
//            System.out.println("---------------------------------");
//            e.printStackTrace();
//        } finally {
//            if (db != null) {
//                db.close();
//            }
//        }


        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_name_Text = edit_Name.getText().toString();
                edit_Account_Text = edit_Account.getText().toString();
                edit_Password_Text = edit_Password.getText().toString();
                edit_Email_Text = edit_Email.getText().toString();
                edit_Phone_Text = edit_Phone.getText().toString();

                if (edit_name_Text != null && !edit_name_Text.equals("") &&edit_Account_Text != null && !edit_Account_Text.equals("") && edit_Password_Text != null && !edit_Password_Text.equals("") && edit_Phone_Text != null && !edit_Phone_Text.equals("") && edit_Email_Text != null && !edit_Email_Text.equals("")) {
                    if (SqlAccountCheck(getApplicationContext(), edit_Account_Text) > 0) {
                        Toast.makeText(view.getContext(), "此帳號已存在!!", Toast.LENGTH_LONG).show();
                    } else {
                        dbHelper = new SqlDataBaseHelper(getApplicationContext());
                        db = dbHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", edit_name_Text);
                        contentValues.put("account", edit_Account_Text);
                        contentValues.put("password", edit_Password_Text);
                        contentValues.put("email", edit_Email_Text);
                        contentValues.put("phone", edit_Phone_Text);
                        contentValues.put("login", login);
                        long rowId = db.insert("caregiver", null, contentValues);
                        if (rowId != -1) {
                            // 数据成功插入
                            Toast.makeText(view.getContext(), "帳號新增成功", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(registration.this, login.class);
                            startActivity(intent);
                            // 其他处理逻辑
                        } else {
                            // 数据插入失败
                            Toast.makeText(view.getContext(), "帳號新增失敗", Toast.LENGTH_LONG).show();
                        }
                        System.out.println("-----------------------------------");
                        System.out.println(edit_Account_Text);

                        db.close();
                    }
                } else {
                    Toast.makeText(view.getContext(), "您還有尚未填寫之欄位", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public Integer SqlAccountCheck(Context context, String newAccount) {
        String DataBaseTable = "caregiver";
        dbHelper = new SqlDataBaseHelper(context);
        db = dbHelper.getReadableDatabase();
        String selection = "account=?";
        String[] selectionArgs = {newAccount};
        Cursor cursor = db.query(DataBaseTable, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}