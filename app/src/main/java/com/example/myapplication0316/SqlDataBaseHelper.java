package com.example.myapplication0316;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class SqlDataBaseHelper extends SQLiteOpenHelper {


    private static final String DataBaseName = "users.db";
    private static final int DataBaseVersion = 1;
    private static final String DataBaseTable1 = "caregiver";
    private static final String DataBaseTable2 = "patient";
//    public static String DataBaseTable = "users";

    public SqlDataBaseHelper(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SqlTable1 = "CREATE TABLE IF NOT EXISTS caregiver (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account TEXT not null," +
                "name TEXT not null," +
                "password TEXT not null," +
                "email TEXT not null," +
                "phone TEXT not null," +
                "patient1 TEXT ," +
                "patient2 TEXT ," +
                "patient3 TEXT ," +
                "photo BLOB ," +
                "login INTEGER not null" +
                ")";
        db.execSQL(SqlTable1);

        String SqlTable2 = "CREATE TABLE IF NOT EXISTS patient (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT ," +
                "level INTEGER ," +
                "birthday TEXT ," +
                "sex TEXT ," +
                "caregiver TEXT ," +
                "photo BLOB " +
                ")";
        db.execSQL(SqlTable2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // 删除旧表
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseTable1);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseTable2);
        // 创建新表
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db,int i,int i1){
        onUpgrade(db,i,i1);
    }
}
