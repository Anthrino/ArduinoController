package com.anthrino.raspicontroller;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;

public class audioPlayback extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {

    private Uri media;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    public static ProgressBar audioDlProgress;
    private Handler handler = new Handler();
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_playback);
        audioDlProgress = (ProgressBar) findViewById(R.id.audioDLProgress);
        audioDlProgress.setMax(100);
        audioDlProgress.setVisibility(View.VISIBLE);
        title = (TextView) findViewById(R.id.audioTitle);
        title.setText(getIntent().getStringExtra("filename"));

        try {

            media = Uri.parse(getIntent().getStringExtra("Media_URL"));
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);
            mediaController = new MediaController(this);
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build());
            mediaPlayer.setDataSource(this, media);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaController.hide();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mediaController.show();
        return false;
    }

    //--MediaPlayerControl methods----------------------------------------------------
    public void start() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
    //--------------------------------------------------------------------------------

    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(findViewById(R.id.audioLayout));
        mediaPlayer.start();
        audioDlProgress.setVisibility(View.GONE);

        handler.post(new Runnable() {
            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }
}