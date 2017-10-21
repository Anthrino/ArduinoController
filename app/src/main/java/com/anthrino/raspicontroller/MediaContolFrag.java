package com.anthrino.raspicontroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mit on 21/10/17.
 */

public class MediaContolFrag extends Fragment {
    public Spinner functionSelector;
    public ListView mediaContentView;
    private String [] dummyAudioData,dummyVideoData,dummyPhotoData;
    ArrayAdapter<String> mediaAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview=inflater.inflate(R.layout.media_control,container,false);
        Spinner functionSelector = (Spinner)rootview.findViewById(R.id.functionSpinner);
        mediaContentView =(ListView) rootview.findViewById(R.id.MediaContentListView);
        dummyAudioData= new String[] {"Audio1","Audio2","Audio3"};
        dummyVideoData= new String[] {"Video1","Video2","Video3"};
        dummyPhotoData= new String[] {"Photo1","Photo2","Photo3"};

        String [] functionList = new String[]{"Select Function", "Audio Storage", "Video Storage", "Photo Storage"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter(rootview.getContext(), android.R.layout.simple_list_item_1, functionList);
        functionSelector.setAdapter(adapter1);

        functionSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                TextView tv = (TextView) rootview.findViewById(R.id.no_selection);
                if (index != 0) {
                    tv.setVisibility(View.GONE);
                    mediaContentView.setVisibility(View.VISIBLE);

                    if (index == 1) {
                        Toast.makeText(rootview.getContext(), "Audio Storage", Toast.LENGTH_SHORT).show();

//                        TODO:
//                          new queryServer().execute("AS_REQ");
                        mediaAdapter=new ArrayAdapter<String>(rootview.getContext(),android.R.layout.simple_list_item_1,dummyAudioData);
                        mediaContentView.setAdapter(mediaAdapter);

                    } else if (index == 2) {
                        Toast.makeText(rootview.getContext(), "Video Storage", Toast.LENGTH_SHORT).show();
//                        TODO:
//                          new queryServer().execute("AS_REQ");

                        mediaAdapter=new ArrayAdapter<String>(rootview.getContext(),android.R.layout.simple_list_item_1,dummyVideoData);
                        mediaContentView.setAdapter(mediaAdapter);

                    }
                    else if (index == 3) {
                        Toast.makeText(rootview.getContext(), "Photo Storage", Toast.LENGTH_SHORT).show();
//                        TODO:
//                        new queryServer().execute("PS_REQ");
                        mediaAdapter=new ArrayAdapter<String>(rootview.getContext(),android.R.layout.simple_list_item_1,dummyPhotoData);
                        mediaContentView.setAdapter(mediaAdapter);
                    }
                }
                else{

                    tv.setVisibility(View.VISIBLE);
                    mediaContentView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootview;
    }
}
