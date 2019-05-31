package com.example.admin.fragment.SqliteAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.fragment.Model.Newspaper;

import java.util.ArrayList;

public class NewspaperDAO {
    private SQLiteDatabase db;
    private Context context;
    private MyDbHelper myDbHelper;

    public NewspaperDAO(Context context)
    {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public void InsertNewspaper(Newspaper newspaper)
    {
        db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", newspaper.Title());
        contentValues.put("count", newspaper.Count());
        db.insert("Newspaper", null, contentValues);
    }

    public ArrayList<Newspaper> getNewspaper()
    {
        ArrayList<Newspaper> ListNewspaper = new ArrayList<Newspaper>();
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Newspaper", null);
        if (cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                int count = cursor.getInt(2);

                Newspaper n = new Newspaper(id, title, count);
                ListNewspaper.add(n);
            }while(cursor.moveToNext());
        }
        return ListNewspaper;
    }

    public void DeleteNewspaper(int id)
    {
        db = myDbHelper.getWritableDatabase();
        db.delete("Newspaper", "_id = ?", new String[]{id + ""});
    }

    public void UpdateBook(Newspaper newspaper)
    {
        db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", newspaper.Title());
        contentValues.put("count", newspaper.Count());
        db.update("Newspaper",contentValues,"_id = ?", new String[]{newspaper.Id() + ""});
    }
}
