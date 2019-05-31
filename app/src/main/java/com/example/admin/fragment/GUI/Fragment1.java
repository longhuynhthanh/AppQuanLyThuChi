package com.example.admin.fragment.GUI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.fragment.Adapter.ListViewBookAdapter;
import com.example.admin.fragment.Model.Book;
import com.example.admin.fragment.R;
import com.example.admin.fragment.SqliteAdapter.BookDAO;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private ListView lvBook;
    private ArrayList<Book> ListBook = new ArrayList<Book>();
    private Button btnInsertBook;
    BookDAO bookDAO;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_1, container, false);

        lvBook = (ListView)rootView.findViewById(R.id.lv_book);
        btnInsertBook = (Button)rootView.findViewById(R.id.btnInsertBook);
        bookDAO = new BookDAO(this.getContext());

        UpdateInterface();

        btnInsertBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerDialog = new AlertDialog.Builder(Fragment1.this.getActivity());
                LayoutInflater inf = getLayoutInflater();
                final View view = inf.inflate(R.layout.dialog_insert_book,null);
                alerDialog.setView(view);

                alerDialog.setNegativeButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_TitleBook = (EditText)view.findViewById(R.id.etTitleBookDialog);
                        EditText et_AuthorBook = (EditText)view.findViewById(R.id.etAuthorDialog);
                        EditText et_PublisherBook = (EditText)view.findViewById(R.id.etPublisherDialog);

                        Book b = new Book(et_TitleBook.getText().toString(), et_AuthorBook.getText().toString(), et_PublisherBook.getText().toString());
                        bookDAO.InsertBook(b);
                        UpdateInterface();

                    }
                });

                alerDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alerDialog.show();
            }
        });

        return rootView;
    }

    public void UpdateInterface()
    {
        ListBook = bookDAO.getBook();
        ListViewBookAdapter listViewBookAdapter = new ListViewBookAdapter(this, ListBook);
        lvBook.setAdapter(listViewBookAdapter);
    }
    public void DeleteBook(int id)
    {
        bookDAO.DeleteBook(id);
        UpdateInterface();
    }
}
