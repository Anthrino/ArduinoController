package com.anthrino.raspicontroller;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private String[] functionList;
    private int selectedFn;
    private TextView fragmentTitle;
    private ViewPager viewpager;
    private selectionPageAdapter customPageAdapter;
    private Toolbar toolbar;
    private TabLayout tabbedLayout;
    private PagerTitleStrip pagerTitleStrip;
    private Spinner functionSelector;
    private String [] dummyAudioData,dummyVideoData,dummyPhotoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentTitle = (TextView) findViewById(R.id.fragmentTitle);
        viewpager = (ViewPager) findViewById(R.id.selection_tabs);
        tabbedLayout=(TabLayout) findViewById(R.id.tabs);

        customPageAdapter = new selectionPageAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewpager.setAdapter(customPageAdapter);
        tabbedLayout.setupWithViewPager(viewpager);




    }

}

