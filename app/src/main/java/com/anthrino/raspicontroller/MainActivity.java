package com.anthrino.raspicontroller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewpager;
    private selectionPageAdapter customPageAdapter;
    private Toolbar toolbar;
    private TabLayout tabbedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager) findViewById(R.id.selection_tabs);
        tabbedLayout = (TabLayout) findViewById(R.id.tabs);

        customPageAdapter = new selectionPageAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewpager.setAdapter(customPageAdapter);
        tabbedLayout.setupWithViewPager(viewpager);

    }

    public void syncState(View v) {
        LightsControlFrag.syncState();
    }
}