package com.anthrino.raspicontroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by mit on 21/10/17.
 */

public class LightsControlFrag extends Fragment {
    public Switch s1, s2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.light_control, container, false);
        s1 = rootview.findViewById(R.id.ledSwitch1);
        s2 = rootview.findViewById(R.id.ledSwitch2);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new queryServer().execute("S1_ON");
                } else {
                    new queryServer().execute("S1_OFF");
                }
            }
        });

        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new queryServer().execute("S2_ON");
                } else {
                    new queryServer().execute("S2_OFF");
                }
            }
        });
        return rootview;

    }
}

