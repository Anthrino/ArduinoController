package com.anthrino.raspicontroller;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by mit on 21/10/17.
 */

public class MediaContolFrag extends Fragment {

    public ListView mediaContentView;
    public View mediaLayout;
    private String[] dummyAudioData, dummyVideoData, dummyPhotoData;
    ArrayAdapter<String> mediaAdapter;
    ImageView imageView;
    VideoView videoView;
    TextView mediaTitle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.media_control, container, false);
        final Spinner functionSelector = rootview.findViewById(R.id.functionSpinner);
        mediaContentView = rootview.findViewById(R.id.MediaContentListView);
        mediaLayout = rootview.findViewById(R.id.mediaLayout);
        imageView = rootview.findViewById(R.id.fragmentImage);
        videoView = rootview.findViewById(R.id.fragmentVideo);
        mediaTitle = rootview.findViewById(R.id.mediaTitle);

        dummyAudioData = new String[]{"Audio1", "Audio2", "Audio3"};
        dummyVideoData = new String[]{"Video1", "Video2", "Video3"};
        dummyPhotoData = new String[]{"Photo1", "Photo2", "Photo3"};

        String[] functionList = new String[]{"Select Function", "Audio Storage", "Video Storage", "Photo Storage"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter(rootview.getContext(), android.R.layout.simple_list_item_1, functionList);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
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
//                          Create new AsyncTask class For Media Contol to fetch data from server
                        mediaAdapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, dummyAudioData);
                        mediaContentView.setAdapter(mediaAdapter);

                    } else if (index == 2) {
                        Toast.makeText(rootview.getContext(), "Video Storage", Toast.LENGTH_SHORT).show();
//                        TODO:
//                          Create new AsyncTask class For Media Contol to fetch data from server

                        mediaAdapter = new ArrayAdapter<String>(rootview.getContext(), android.R.layout.simple_list_item_1, dummyVideoData);
                        mediaContentView.setAdapter(mediaAdapter);

                    } else if (index == 3) {
                        Toast.makeText(rootview.getContext(), "Photo Storage", Toast.LENGTH_SHORT).show();
//                        TODO:
//                          Create new AsyncTask class For Media Contol to fetch data from server

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

//                String mediaURL = "http://139.59.65.16:8000/" + functionSelector.getSelectedItem().toString() + "/" + fileName;
                String mediaURL = "https://r2---sn-5hne6nse.googlevideo.com/videoplayback?ei=RQDtWcawBcGk1gL866nQDQ&expire=1508725925&mime=video%2Fmp4&key=yt6&initcwndbps=3203750&mn=sn-5hne6nse&signature=34738E716FCEBBE73133126E53D3B340886E4AF6.C4B953E61DBE80335BE581371129C1A75B356730&ipbits=0&mm=31&ip=37.58.82.171&itag=22&ms=au&mt=1508704158&mv=m&id=o-AH2a_ZxtFD9illX2lnou78FM97guw2uKChW8x-MLxJor&pl=20&source=youtube&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&requiressl=yes&nh=IgpwcjA0LmFtczE1KgkxMjcuMC4wLjE&ratebypass=yes&dur=122.345&lmt=1508380629883498&title=Introducing+the+new+Surface+Book+2";
                Uri media = Uri.parse(mediaURL);

                try {

                    if (functionSelector.getSelectedItemPosition() == 3) {
                        imageView.setVisibility(View.VISIBLE);
                        videoView.setVisibility(View.GONE);
                        new mediaStreamService().execute(new String[]{"test", "photo"});
                        imageView.setImageBitmap(mediaObject.photo);

                    } else if (functionSelector.getSelectedItemPosition() != 0){
                        videoView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                        MediaController mediacontroller = new MediaController(getActivity());
                        mediacontroller.setAnchorView(videoView);
                        videoView.setMediaController(mediacontroller);
                        videoView.setVideoURI(media);
                        videoView.requestFocus();
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
