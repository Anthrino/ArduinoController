package com.anthrino.raspicontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mit on 21/10/17.
 */

public class MediaContolFrag extends Fragment {
    public static boolean downLoadProgress = false;
    //    public static ProgressBar downloadProgressbar;
    public ListView mediaContentView;
    public View mediaLayout;
    private String[] dummyAudioData, dummyVideoData, dummyPhotoData;
    ArrayAdapter<String> mediaAdapter;
    //    public static ImageView imageView;
//    VideoView videoView;
    TextView mediaTitle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.media_control, container, false);
        final Spinner functionSelector = rootview.findViewById(R.id.functionSpinner);
        mediaContentView = rootview.findViewById(R.id.MediaContentListView);
        mediaLayout = rootview.findViewById(R.id.mediaLayout);
//        videoView = rootview.findViewById(R.id.fragmentVideo);
        mediaTitle = rootview.findViewById(R.id.mediaTitle);
//        downloadProgressbar=rootview.findViewById(R.id.downloadProgressBar);
//        downloadProgressbar.setMax(100);

        dummyAudioData = new String[]{"BATMAN_BEGINS.mp3", "GOT.mp3",};
        dummyVideoData = new String[]{"Shell_Sort.mp4", "Insertion_Sort.mp4", "Binary_Search.mp4"};
        dummyPhotoData = new String[]{"vinay.jpeg", "jerin.jpeg", "mit.jpeg", "jay.jpeg", "meshde.png"};

        String[] functionList = new String[]{"Select Function", "Audio Storage", "Video Storage", "Photo Storage"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter(rootview.getContext(), android.R.layout.simple_list_item_1, functionList);

        functionSelector.setAdapter(adapter1);

        functionSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                TextView tv = rootview.findViewById(R.id.no_selection);
                mediaLayout.setVisibility(View.GONE);
                if (index != 0) {
                    tv.setVisibility(View.GONE);
                    mediaContentView.setVisibility(View.VISIBLE);
                    if (index == 1) {
                        Toast.makeText(rootview.getContext(), "Audio Storage", Toast.LENGTH_SHORT).show();

//                        TODO: Create new AsyncTask class For getting Audio list from server
                        mediaAdapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, dummyAudioData);
                        mediaContentView.setAdapter(mediaAdapter);

                    } else if (index == 2) {
                        Toast.makeText(rootview.getContext(), "Video Storage", Toast.LENGTH_SHORT).show();
//                        TODO: Create new AsyncTask class For getting Video list from server

                        mediaAdapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, dummyVideoData);
                        mediaContentView.setAdapter(mediaAdapter);

                    } else if (index == 3) {
                        Toast.makeText(rootview.getContext(), "Photo Storage", Toast.LENGTH_SHORT).show();
//                        TODO: Create new AsyncTask class For getting Photo list from server

                        mediaAdapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, dummyPhotoData);
                        mediaContentView.setAdapter(mediaAdapter);
                    }
                } else {

                    tv.setVisibility(View.VISIBLE);
                    mediaContentView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mediaContentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String fileName = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(rootview.getContext(), "Streaming " + fileName, Toast.LENGTH_SHORT).show();
//                mediaContentView.setVisibility(View.GONE);
//                mediaLayout.setVisibility(View.VISIBLE);
//                mediaTitle.setText(fileName);
                String mediaURL = "http://139.59.65.16:8000/static/";

                try {

                    if (functionSelector.getSelectedItemPosition() == 3) {
                        mediaURL = mediaURL.concat("photos/" + fileName);
                        Intent intent = new Intent(getActivity(), photoDisplay.class);
                        intent.putExtra("Media_URL", mediaURL);
                        intent.putExtra("filename", fileName);
                        startActivity(intent);
                    } else if (functionSelector.getSelectedItemPosition() == 2) {
                        mediaURL = mediaURL.concat("videos/" + fileName);
                        Intent intent = new Intent(getActivity(), videoPlayback.class);
                        intent.putExtra("Media_URL", mediaURL);
                        intent.putExtra("filename", fileName);
                        startActivity(intent);
                    } else if (functionSelector.getSelectedItemPosition() == 1) {
                        mediaURL = mediaURL.concat("music/" + fileName);
                        Intent intent = new Intent(getActivity(), audioPlayback.class);
                        intent.putExtra("Media_URL", mediaURL);
                        intent.putExtra("filename", fileName);
                        startActivity(intent);
                    }
                } catch (Exception e) {
//                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        return rootview;
    }

}
