package com.example.admin.fragment.SqliteAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import com.example.admin.fragment.Model.Book;

import java.util.ArrayList;

public class BookDAO {
    private SQLiteDatabase db;
    private Context context;
    private MyDbHelper myDbHelper;

    public BookDAO(Context context)
    {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public void InsertBook(Book book)
    {
        db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", book.Title());
        contentValues.put("author", book.Author());
        contentValues.put("publisher", book.Publisher());
        db.insert("Book", null, contentValues);
    }
   
    public ArrayList<Book> getBook()
    {
        ArrayList<Book> ListBook = new ArrayList<Book>();
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book", null);
        if (cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String author = cursor.getString(2);
                String publisher = cursor.getString(3);
                Book b = new Book(id, title, author, publisher);
                ListBook.add(b);
            }while(cursor.moveToNext());
        }
        return ListBook;
    }

    public void DeleteBook(int id)
    {
        db = myDbHelper.getWritableDatabase();
        db.delete("Book", "_id = ?", new String[]{id + ""});
    }

    public void UpdateBook(Book book)
    {
        db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", book.Title());
        contentValues.put("author", book.Author());
        contentValues.put("publisher", book.Publisher());
        db.update("Book",contentValues,"_id = ?", new String[]{book.Id() + ""});
    }
}
