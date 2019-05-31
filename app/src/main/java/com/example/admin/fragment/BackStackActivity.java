package com.example.admin.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.fragment.GUI.Fragment1;
import com.example.admin.fragment.GUI.Fragment2;

public class BackStackActivity extends AppCompatActivity {
    private Button btnFrag1;
    private Button btnFrag2;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_backstack);

        btnFrag1 = (Button)findViewById(R.id.btn_replace_fragment1);
        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Fragment1());
            }
        }) ;
        btnFrag2 = (Button)findViewById(R.id.btn_replace_fragment2);
        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new Fragment2());
            }
        });

//
        initFragment();
    }

    private void initFragment() {

        Fragment1 firstFragment = new Fragment1();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.container_body, firstFragment);

        ft.commit();

    }



    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getSupportFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.container_body, fragment);

            ft.commit();

        }

    }

    protected void addFragment(Fragment fragment) {

        FragmentManager fmgr = getSupportFragmentManager();

        FragmentTransaction ft = fmgr.beginTransaction();

        ft.add(R.id.container_body, fragment);

        ft.addToBackStack(fragment.getClass().getSimpleName());

        ft.commit();

    }
}
