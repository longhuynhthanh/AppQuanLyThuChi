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

import com.example.admin.fragment.GUI.Fragment2;
import com.example.admin.fragment.Model.Newspaper;
import com.example.admin.fragment.R;
import com.example.admin.fragment.SqliteAdapter.NewspaperDAO;

import java.util.ArrayList;

public class ListViewNewspaperAdapter extends BaseAdapter {

    private Fragment fragment;
    private ArrayList<Newspaper> ListNewspaper;

    public ListViewNewspaperAdapter(Fragment fragment, ArrayList<Newspaper> ListNewspaper)
    {
        this.fragment = fragment;
        this.ListNewspaper = ListNewspaper;
    }


    @Override
    public int getCount() {
        return this.ListNewspaper.size();
    }

    @Override
    public Object getItem(int position) {
        return this.ListNewspaper.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // gọi layout infater để ảnh xạ view vào data
        LayoutInflater inflater = fragment.getLayoutInflater();

        // đổ dữ liệu vào biến view, biến này chính là các view nằm trong view item_newspaper.xml
        convertView = inflater.inflate(R.layout.item_newspaper, null);

        // đặt chữ cho từng view trong danh sách
        TextView tvID = (TextView)convertView.findViewById(R.id.tv_idNewspaper);
        tvID.setText("ID: " + this.ListNewspaper.get(position).Id() + "");
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tv_titleNewspaper);
        tvTitle.setText("Tên Tạp Chí: " + this.ListNewspaper.get(position).Title());
        TextView tvCount = (TextView)convertView.findViewById(R.id.tv_CountNewspaper);
        tvCount.setText("Số Lượng: " + this.ListNewspaper.get(position).Count() + "");


        ImageView imgUpdateNewspaper = (ImageView) convertView.findViewById(R.id.ivUpdateNewspaper);
        ImageView imgDeleteNewspaper = (ImageView) convertView.findViewById(R.id.ivDeleteNewspaper);

        final Newspaper newspaper = this.ListNewspaper.get(position);

        imgDeleteNewspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = newspaper.Id();
                ((Fragment2) fragment).DeleteNewspaper(id);
            }
        });
        imgUpdateNewspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                final View view1 = fragment.getActivity().getLayoutInflater().inflate(R.layout.dialog_insert_newspaper, null);
                builder.setView(view1);
                final TextView tv_IdNewspaper = (TextView) view1.findViewById(R.id.tvIdNewspaperDialog);
                final EditText et_TitleNewspaper = (EditText) view1.findViewById(R.id.etTitleNewspaperDialog);
                final EditText et_CountNewspaper = (EditText) view1.findViewById(R.id.etCountNewspaperDialog);



                tv_IdNewspaper.setText(newspaper.Id() + "");
                et_TitleNewspaper.setText(newspaper.Title());
                et_CountNewspaper.setText(newspaper.Count() + "");

                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Newspaper NewspaperUpdate = new Newspaper(Integer.parseInt(tv_IdNewspaper.getText().toString()), et_TitleNewspaper.getText().toString(), Integer.parseInt(et_CountNewspaper.getText().toString()));
                        NewspaperDAO newpaperDAO = new NewspaperDAO(fragment.getActivity());
                        newpaperDAO.UpdateBook(NewspaperUpdate);
                        ((Fragment2)fragment).UpdateInterface();
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


        // trả về kết quả
        return convertView;
    }
}
