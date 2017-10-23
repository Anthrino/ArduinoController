package com.anthrino.raspicontroller;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Johns on 10/23/2017.
 */

public class mediaStreamService extends AsyncTask<String, Void, Integer> {

    private static ProgressBar prSpinner;

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Integer doInBackground(String... strings) {

//        prSpinner = (ProgressBar)findViewById(R.id.progressBar1);

        if (strings[0].equals("test")) {
            try {
                if (strings[1].equals("photo")) {
                    Log.d("Photo DOWNLOADING","Here");
                    URL photo = new URL(strings[2]);
                    HttpURLConnection connection = (HttpURLConnection) photo.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    if(isCancelled())
                        return new Integer(1);
                    mediaObject.photo = BitmapFactory.decodeStream(inputStream);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{

        }

        if(strings[0].equals("test") && strings[1].equals("photo")) {
            Log.d("Photo Down FLag ","True");
            photoDisplay.downloadProgress = true;
        }
        else{
            //For audio/video
        }
        return new Integer(1);
    }

    protected void onPostExecute(Integer val){

    }

}
