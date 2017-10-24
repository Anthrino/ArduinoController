package com.anthrino.raspicontroller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mit on 21/10/17.
 */

public class selectionPageAdapter extends FragmentPagerAdapter {

    public selectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                LightsControlFrag frag1 = new LightsControlFrag();
                return frag1;
            case 1:
                MediaContolFrag frag2 = new MediaContolFrag();
                return frag2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Lights";
            case 1:
                return "Media";
        }
        return null;
    }
}

