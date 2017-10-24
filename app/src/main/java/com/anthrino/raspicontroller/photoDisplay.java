package com.anthrino.raspicontroller;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by mit on 23/10/17.
 */

public class photoDisplay extends AppCompatActivity {

    public static ImageView imageView;
    public static TextView title;
    public static ProgressBar downloadProgressBar;
    public static boolean downloadProgress = false;
    public static mediaStreamService task1;
    public static imageViewSetup task2;
    private Uri media;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);

        imageView = (ImageView) findViewById(R.id.fragmentImage);
        title = (TextView) findViewById(R.id.photoTitle);
        title.setText(getIntent().getStringExtra("filename"));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        downloadProgressBar = (ProgressBar) findViewById(R.id.photodownloadProgressBar);
        downloadProgressBar.setMax(100);
        downloadProgressBar.setVisibility(View.VISIBLE);

        String mediaURL = getIntent().getStringExtra("Media_URL");
        Log.d("Photo", mediaURL);
//        imageView.setVisibility(View.GONE);
        task1 = new mediaStreamService();
        task2 = new imageViewSetup();

        task1.execute(new String[]{"test", "photo", mediaURL});
        task2.execute(downloadProgressBar);

    }

    public static void setupPhoto() {
        Log.d("Photo", "here");
        imageView.setImageBitmap(mediaObject.photo);
        imageView.setVisibility(View.VISIBLE);
        downloadProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        task1.cancel(true);
        task2.cancel(true);
        super.onBackPressed();
    }


}
