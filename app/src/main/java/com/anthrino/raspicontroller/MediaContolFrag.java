package com.anthrino.raspicontroller;

import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mit on 21/10/17.
 */

public class MediaContolFrag extends Fragment {

    public ListView mediaContentView;
    public View mediaLayout;
    String mediaMode;
    private String[] dummyAudioData, dummyVideoData, dummyPhotoData;
    ArrayAdapter<String> mediaAdapter;
    MediaController mediacontroller;
    ImageView imageView;
    VideoView videoView;
    TextView mediaTitle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.media_control, container, false);
        Spinner functionSelector = rootview.findViewById(R.id.functionSpinner);
        mediaContentView = rootview.findViewById(R.id.MediaContentListView);
        mediaLayout = rootview.findViewById(R.id.mediaLayout);
        imageView = rootview.findViewById(R.id.fragmentImage);
        videoView = rootview.findViewById(R.id.fragmentVideo);
        mediaTitle = rootview.findViewById(R.id.mediaTitle);
        mediacontroller = rootview.findViewById(R.id.mediaCtrller);

        dummyAudioData = new String[]{"Audio1", "Audio2", "Audio3"};
        dummyVideoData = new String[]{"Video1", "Video2", "Video3"};
        dummyPhotoData = new String[]{"Photo1", "Photo2", "Photo3"};

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

//                        TODO:
//                          new queryServer().execute("AS_REQ");
                        mediaMode = "Audio";
                        mediaAdapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, dummyAudioData);
                        mediaContentView.setAdapter(mediaAdapter);

                    } else if (index == 2) {
                        Toast.makeText(rootview.getContext(), "Video Storage", Toast.LENGTH_SHORT).show();
//                        TODO:
//                          new queryServer().execute("AS_REQ");

                        mediaMode = "Video";
                        mediaAdapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, dummyVideoData);
                        mediaContentView.setAdapter(mediaAdapter);

                    } else if (index == 3) {
                        Toast.makeText(rootview.getContext(), "Photo Storage", Toast.LENGTH_SHORT).show();
//                        TODO:
//                        new queryServer().execute("PS_REQ");

                        mediaMode = "Photo";
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
                mediaContentView.setVisibility(View.GONE);
                mediaLayout.setVisibility(View.VISIBLE);
                mediaTitle.setText(fileName);

//                String mediaURL = "http://139.59.65.16:8000/" + mediaMode + "/" + fileName;
                String mediaURL = "https://www.youtube.com/watch?v=HCYCvREMcCM";
                Uri media = Uri.parse(mediaURL);

                try {

                    if (mediaMode == "Photo") {
                        imageView.setVisibility(View.VISIBLE);
                        videoView.setVisibility(View.GONE);

                        URL photo = new URL(photoURL);
                        imageView.setImageBitmap(new queryServer().execute(new String[]{"test","photo"}));
                        url = new URL("http://studio52.tv/wp-content/uploads/2017/01/%5E313DF87713919104CECDC0E72AB4E58999A066F9F03EFE6311%5Epimgpsh_fullsize_distr.jpg");
                        conn = (HttpURLConnection) url.openConnection();
                        HttpURLConnection connection = (HttpURLConnection) photo.openConnection();
                        InputStream inputStream = connection.getInputStream();
                        return BitmapFactory.decodeStream(inputStream);

                    } else {
                        videoView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);

                        mediacontroller.setAnchorView(videoView);
                        videoView.setMediaController(mediacontroller);
                        videoView.setVideoURI(media);
                        videoView.requestFocus();
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            // Close the progress bar and play the video
                            public void onPrepared(MediaPlayer mp) {
                                videoView.start();
                            }
                        });
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
