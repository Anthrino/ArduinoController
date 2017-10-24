package com.anthrino.raspicontroller;

import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by mit on 23/10/17.
 */

public class imageViewSetup extends AsyncTask<ProgressBar, Integer, Integer> {


    public static ProgressBar progressBar;

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected Integer doInBackground(ProgressBar... params) {
        progressBar = params[0];

        int i = 10;
        while (photoDisplay.downloadProgress == false) {
            publishProgress(i);
            i = (i + 10) % 100;
            if (isCancelled())
                break;

        }
        return new Integer(1);
    }

    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }

    protected void onPostExecute(Integer val) {
        photoDisplay.setupPhoto();
    }

}

