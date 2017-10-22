package com.anthrino.raspicontroller;

import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mit on 21/10/17.
 */

public class queryServer extends AsyncTask<String,Void,Long> {
    URL url;
    URLConnection urlConnection;
    HttpURLConnection conn;
    protected void onPreExecute()
    {
        try{
            url = new URL("http://139.59.65.16:8000/");}
        catch(Exception e){
            Log.d("Networking",e.toString());}

    }
    @Override
    protected Long doInBackground(String... params)
    {
        try {
            conn = (HttpURLConnection) url.openConnection();
        }
        catch(Exception e) {
            Log.d("Networking url",e.toString());

        }
        try{

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoOutput(true);
//            params[0] is the query code

            JSONObject data = new JSONObject();
            data.put("body",params[0]);
            data.put("from","Nan");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//            BufferedReader br;
//            try{
//                 br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            }
//            catch (Exception e){
//                Log.d("Networking Response",e.toString());
//            }
            wr.write(data.toString());
            wr.flush();
            Log.d("Networking Response",params[0]);
            Log.d("Networking Response","Data Sent and flushed");
//            StringBuilder result=new StringBuilder();
//            String line;
//            while((line=br.readLine()) != null)
//            {
//                result.append(line);
//
//            }
//            Log.d("Networking Response",conn.getResponseMessage());
//            Log.d("Networking Response",result.toString());

            //TODO: Find a way to send received data from server to our listview.

        }
        catch(Exception e){
            Log.d("Networking",e.toString());}
        return new Long(5);
    }
}
