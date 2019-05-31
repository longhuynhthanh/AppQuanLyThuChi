package com.example.admin.fragment.SqliteAdapter;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;

public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper( Context context) {
        super(context, "quanlythuvien", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
            create table Book
            (
                _id integer primary key auto increment
                title text,
                author text,
                publisher text
            )
         */
        String sql = "create table Book" +
                "            (" +
                "                _id integer primary key autoincrement," +
                "                title text," +
                "                author text," +
                "                publisher text" +
                "            )";
        db.execSQL(sql);


        // tạo database cho lớp newspaper
        /*
            create table newspaper
            (
                _id integer primary key autincreament,
                title text,
                count int
            )
         */
        String sql2 = "create table Newspaper" +
                "            (" +
                "                _id integer primary key autoincrement," +
                "                title text," +
                "                count integer" +
                "            )";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Newspaper");
        onCreate(db);
    }
}
