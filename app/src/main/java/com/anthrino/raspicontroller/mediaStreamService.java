package com.anthrino.raspicontroller;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                    Log.d("Photo DOWNLOADING", "Here");
                    URL photo = new URL(strings[2]);
                    HttpURLConnection connection = (HttpURLConnection) photo.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    if (isCancelled())
                        return new Integer(1);
                    mediaObject.photo = BitmapFactory.decodeStream(inputStream);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (strings[0].equals("meta") && mediaObject.imageList.size() == 0) {
            try {
                URL mreq = new URL(strings[1]);
                HttpURLConnection connection = (HttpURLConnection) mreq.openConnection();
                InputStreamReader in = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(in);
                StringBuilder content = new StringBuilder();
                String line;
                while (null != (line = br.readLine())) {
                    content.append(line);
                }
                JSONObject jsonResp = new JSONObject(content.toString());
                JSONArray audioList = (JSONArray) jsonResp.get("music");
                JSONArray videoList = (JSONArray) jsonResp.get("videos");
                JSONArray imageList = (JSONArray) jsonResp.get("photos");

                int arraySize = audioList.length();
                for (int i = 0; i < arraySize; i++) {
                    mediaObject.audioList.add(i, (String) audioList.get(i));
                }

                arraySize = videoList.length();
                for (int i = 0; i < arraySize; i++) {
                    mediaObject.videoList.add(i, (String) videoList.get(i));
                }

                arraySize = imageList.length();
                for (int i = 0; i < arraySize; i++) {
                    mediaObject.imageList.add(i, (String) imageList.get(i));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (strings[0].equals("test") && strings[1].equals("photo")) {
            Log.d("Photo Down FLag ", "True");
            photoDisplay.downloadProgress = true;
        } else {
            //For audio/video
        }
        return new Integer(1);
    }

    protected void onPostExecute(Integer val) {

    }

}
