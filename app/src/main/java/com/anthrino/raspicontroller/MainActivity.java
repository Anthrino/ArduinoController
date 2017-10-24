package com.anthrino.raspicontroller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] functionList;
    private Switch s1, s2;
    private int selectedFn;
    private TextView fragmentTitle;
    private ViewPager viewpager;
    private selectionPageAdapter customPageAdapter;
    private Toolbar toolbar;
    private TabLayout tabbedLayout;
    private PagerTitleStrip pagerTitleStrip;
    private Spinner functionSelector;
    private String[] dummyAudioData, dummyVideoData, dummyPhotoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTitle = (TextView) findViewById(R.id.fragmentTitle);
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