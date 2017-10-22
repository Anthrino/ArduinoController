package com.anthrino.raspicontroller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mit on 21/10/17.
 */

public class LightsControlFrag extends Fragment {
    public static Switch s1, s2;
    public static LinearLayout lightlayout;
    public static ProgressBar progressBar;
    public static boolean progressFlag = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.light_control, container, false);
        s1 = (Switch)rootview.findViewById(R.id.ledSwitch1);
        s2 = (Switch)rootview.findViewById(R.id.ledSwitch2);
        lightlayout=(LinearLayout) rootview.findViewById(R.id.lightLayout);
        progressBar =(ProgressBar)rootview.findViewById(R.id.progressBar);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new queryServer().execute("light","control","1","ON");
                } else {
                    new queryServer().execute("light","control","1","OFF");
                }
            }
        });

        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new queryServer().execute("light","control","2","ON");
                } else {
                    new queryServer().execute("light","control","2","OFF");
                }
            }
        });


        return rootview;

    }
    public static void syncState(){
        progressFlag=false;
        lightlayout.setClickable(false);
        progressBar.setMax(100);
        progressBar.setVisibility(View.VISIBLE);

        new queryServer().execute("light","status");
        int i=10;
        while(progressFlag){
            progressBar.setProgress(i);
            i=(i+10)%100;

        }
        progressBar.setVisibility(View.GONE);
        lightlayout.setClickable(true);
    }



    public static void updateState(String stauses []){
        if(stauses[0].equals("ON"))
            s1.setChecked(true);
        else if(stauses[0].equals("OFF"))
            s1.setChecked(false);

        if(stauses[1].equals("ON"))
            s2.setChecked(true);
        else if(stauses[1].equals("OFF"))
            s2.setChecked(false);

        lightlayout.setClickable(true);




    }

}

