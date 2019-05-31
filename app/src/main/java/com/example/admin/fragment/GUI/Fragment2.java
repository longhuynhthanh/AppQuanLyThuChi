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

import com.example.admin.fragment.Adapter.ListViewNewspaperAdapter;
import com.example.admin.fragment.Model.Newspaper;
import com.example.admin.fragment.R;
import com.example.admin.fragment.SqliteAdapter.NewspaperDAO;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private ListView lvNewspaper;
    private ArrayList<Newspaper> ListNewspaper = new ArrayList<Newspaper>();
    private Button btnInsertNewspaper;
    private NewspaperDAO newspaperDAO;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_2, container, false);

        lvNewspaper = (ListView)rootView.findViewById(R.id.lv_newspaper);
        btnInsertNewspaper = (Button)rootView.findViewById(R.id.btnInsertNewspaper);
        newspaperDAO = new NewspaperDAO(this.getContext());

        UpdateInterface();
        btnInsertNewspaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerDialog = new AlertDialog.Builder(Fragment2.this.getActivity());
                LayoutInflater inf = getLayoutInflater();
                final View view = inf.inflate(R.layout.dialog_insert_newspaper,null);
                alerDialog.setView(view);

                alerDialog.setNegativeButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_TitleNewspaper = (EditText)view.findViewById(R.id.etTitleNewspaperDialog);
                        EditText et_CountNewspaper = (EditText)view.findViewById(R.id.etCountNewspaperDialog);


                        Newspaper newspaper = new Newspaper(et_TitleNewspaper.getText().toString(), Integer.parseInt(et_CountNewspaper.getText().toString()));
                        newspaperDAO.InsertNewspaper(newspaper);
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
        ListNewspaper = newspaperDAO.getNewspaper();
        ListViewNewspaperAdapter listViewNewspaperAdapter = new ListViewNewspaperAdapter(this, ListNewspaper);
        lvNewspaper.setAdapter(listViewNewspaperAdapter);
    }

    public void DeleteNewspaper(int id)
    {
        newspaperDAO.DeleteNewspaper(id);

        UpdateInterface();
    }
}
