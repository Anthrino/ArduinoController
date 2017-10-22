package com.anthrino.raspicontroller;

import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mit on 21/10/17.
 */

public class queryServer extends AsyncTask<String,Void,String []> {
    URL url;
    URLConnection urlConnection;
    HttpURLConnection conn;
    JSONObject data;
    String status []=new String []{"sample1","sample2"};
    protected void onPreExecute()
    {
        data = new JSONObject();
    }
    @Override
    protected String [] doInBackground(String... params)
    {
        try {
            url = new URL("http://139.59.65.16:8000/light");
            conn = (HttpURLConnection) url.openConnection();
        }
        catch(Exception e) {
            Log.d("Networking url",e.toString());

        }
        try{

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoOutput(true);

            // Creating Request:

                if(params[1].equals("control")) {
                    data.put("query", params[1]);
                    data.put("light", Integer.parseInt(params[2]));
                    data.put("status", params[3]);
                }
                else if(params[1].equals("status")){
                    data.put("query",params[1]);
                }


            Log.d("Networking Request", data.toString());

            // Sending HTTP Post Request
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data.toString());
                wr.flush();
                Log.d("Networking Response", conn.getResponseMessage());

            // Receiving Data from Server
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader r = new BufferedReader(in);
            if(params[1].equals("status"))
            {
                String x = "";
                x = r.readLine();
                String total = "";

                while(x!= null){
                    total += x;
                    x = r.readLine();
                }
                Log.d("Networking Received",total);

                JSONObject recvdData = new JSONObject(total);
                String light1Status=recvdData.getString("1");
                String light2Status=recvdData.getString("2");

                status=new String[] {light1Status,light2Status};
            }



            //TODO: Find a way to send received data from server to our listview.

        }
        catch(Exception e){
            Log.d("Networking",e.toString());}
        return status;
    }

    protected void onPostExecute(String [] statuses){
        LightsControlFrag.updateState(statuses);
        LightsControlFrag.progressFlag=true;
    }
}
