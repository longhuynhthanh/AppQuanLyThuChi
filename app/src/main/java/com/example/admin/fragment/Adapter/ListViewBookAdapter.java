package com.example.admin.fragment.Adapter;


import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.fragment.GUI.Fragment1;
import com.example.admin.fragment.Model.Book;
import com.example.admin.fragment.R;
import com.example.admin.fragment.SqliteAdapter.BookDAO;


import java.util.ArrayList;



public class ListViewBookAdapter extends BaseAdapter {

    private Fragment fragment;
    private ArrayList<Book> ListBook;

    public ListViewBookAdapter(Fragment fragment, ArrayList<Book> ListBook)
    {
        this.ListBook = ListBook;
        this.fragment = fragment;
    }
    @Override
    public int getCount() {
        return this.ListBook.size();
    }

    @Override
    public Object getItem(int position) {
        return this.ListBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // gọi layout infater để ảnh xạ view vào data
        LayoutInflater inflater = fragment.getLayoutInflater();

        // đổ dữ liệu vào biến view, biến này chính là các view nằm trong view item_book.xml
        convertView = inflater.inflate(R.layout.item_book, null);

        // Đặt chữ cho từng view trong danh sách
        TextView tvId = (TextView) convertView.findViewById(R.id.tv_idBook);
        tvId.setText("ID: " + ListBook.get(position).Id() + "");
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_titleBook);
        tvTitle.setText("Tiêu Đề: " + ListBook.get(position).Title());
        TextView tvAuthor = (TextView) convertView.findViewById(R.id.tv_Author);
        tvAuthor.setText("Tác Giả: " + ListBook.get(position).Author());
        TextView tvPublisher = (TextView) convertView.findViewById(R.id.tv_Publisher);
        tvPublisher.setText("Nhà Xuất Bản: " + ListBook.get(position).Publisher());

        ImageView imgUpdate = (ImageView) convertView.findViewById(R.id.ivUpdate);
        ImageView imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

        final Book book = ListBook.get(position);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = book.Id();
                ((Fragment1) fragment).DeleteBook(id);
            }
        });
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                final View view1 = fragment.getActivity().getLayoutInflater().inflate(R.layout.dialog_insert_book, null);
                builder.setView(view1);
                final TextView tv_IdBook = (TextView) view1.findViewById(R.id.tvIdBookDialog);
                final EditText et_TitleBook = (EditText) view1.findViewById(R.id.etTitleBookDialog);
                final EditText et_Author = (EditText) view1.findViewById(R.id.etAuthorDialog);
                final EditText et_Publsiher = (EditText) view1.findViewById(R.id.etPublisherDialog);



                tv_IdBook.setText(book.Id() + "");
                et_TitleBook.setText(book.Title());
                et_Author.setText(book.Author());
                et_Publsiher.setText(book.Publisher());
                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Book BookUpdate = new Book(Integer.parseInt(tv_IdBook.getText().toString()), et_TitleBook.getText().toString(), et_Author.getText().toString(), et_Publsiher.getText().toString());
                        BookDAO bookDAO = new BookDAO(fragment.getActivity());
                        bookDAO.UpdateBook(BookUpdate);
                        ((Fragment1)fragment).UpdateInterface();
                    }
                });
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        // trả về view kết quả
        return convertView;
    }
}


