package com.anthrino.raspicontroller;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by mit on 23/10/17.
 */

public class videoPlayback extends AppCompatActivity {

    private VideoView videoView;
    public static TextView title;
    private Uri media;
    public static ProgressBar videoDownloadProgressBar;
    public static LinearLayout frameLayout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_playback);
        videoView = (VideoView) findViewById(R.id.fragmentVideo);
        frameLayout = (LinearLayout) findViewById(R.id.videoLayout);
        title = (TextView) findViewById(R.id.videoTitle);
        title.setText(getIntent().getStringExtra("filename"));
        videoDownloadProgressBar = (ProgressBar) findViewById(R.id.videoDownloadProgressbar);
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
                videoDownloadProgressBar.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
                videoView.start();

            }
        });

    }
}
