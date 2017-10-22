package com.anthrino.raspicontroller;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Johns on 10/23/2017.
 */

public class mediaStreamService extends AsyncTask<String, Void, Void> {

//    private static ProgressBar prSpinner;
//
//    @Override
//    protected void onPreExecute() {
//        this.prSpinner.show();
//    }

    @Override
    protected Void doInBackground(String... strings) {

//        prSpinner = (ProgressBar)findViewById(R.id.progressBar1);

        if (strings[0] == "test") {
            try {
                if (strings[1] == "photo") {
                    URL photo = new URL("https://www.hdwallpapers.in/walls/facebook_world_network-HD.jpg");
                    HttpURLConnection connection = (HttpURLConnection) photo.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    mediaObject.photo = BitmapFactory.decodeStream(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{

        }
        return null;
    }
//
//    protected void onProgressUpdate(Integer... values) {
//        txt.setText("Running..."+ values[0]);
//        progressBar.setProgress(values[0]);
//    }
}
