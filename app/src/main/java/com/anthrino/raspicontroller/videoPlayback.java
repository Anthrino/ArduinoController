package com.anthrino.raspicontroller;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.net.URL;

/**
 * Created by mit on 23/10/17.
 */

public class videoPlayback extends AppCompatActivity {

    private VideoView videoView;
    private Uri media;
    public static ProgressBar videoDownloadProgressBar;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_playback);
        videoView = (VideoView) findViewById(R.id.fragmentVideo);
        videoDownloadProgressBar=(ProgressBar)findViewById(R.id.videoDownloadProgressbar);
        videoDownloadProgressBar.setMax(100);
        videoDownloadProgressBar.setVisibility(View.VISIBLE);
        media = Uri.parse(getIntent().getStringExtra("Media_URL"));
        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(media);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                videoDownloadProgressBar.setVisibility(View.GONE);

            }
        });

    }
}
