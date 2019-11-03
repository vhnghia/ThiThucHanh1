package com.example.studentcontent;

import android.content.Context;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "student_sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table Students(id integer primary key, ten text, khoa text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Students");
        onCreate(sqLiteDatabase);
    }
}
