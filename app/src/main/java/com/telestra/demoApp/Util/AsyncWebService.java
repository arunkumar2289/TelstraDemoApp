package com.telestra.demoApp.Util;

import android.content.Context;
import android.os.AsyncTask;

import com.telestra.demoApp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class used to call url and get data from server by GET method
 * For this we have used AsyncTask
 * Interface is used to pass data after the execution of AsyncTask
 *
 */
public class AsyncWebService  {


    private DataDownload interfaceData;  //Interface used to send data

    private Context context; //Context from activity


    //Constructor class
    public AsyncWebService(DataDownload interfaceData,Context context){
        this.interfaceData = interfaceData;
        this.context = context;
        new AsyncWebServiceTask().execute();

    }

    //Async task for GET method
    private class AsyncWebServiceTask extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream;
            String result = "";
            int resCode ;

            try {
                URL url = new URL(context.getString(R.string.url));//Url from strings.xml
                URLConnection urlConn = url.openConnection();

                HttpURLConnection httpConn = (HttpURLConnection)urlConn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                httpConn.setConnectTimeout(15000);
                resCode = httpConn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpConn.getInputStream();
                    result = convertInputStreamToString(inputStream);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }


       //After successful execution of Async Task

        @Override
        protected void onPostExecute(String result) {
            interfaceData.receivedData(result);
        }
    }

    //Getting data from InputStream
    private String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
