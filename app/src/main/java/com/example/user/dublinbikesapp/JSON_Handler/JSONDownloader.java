/*
Developers:
Siddarth Nair - 10376001
Sudhanva Hukkeri - 10383120
Marcel Kolbus - 10374813
Nitanshu Rehani - 10382675
Supriya Pawar - 10363812
*/
package com.example.user.dublinbikesapp.JSON_Handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class JSONDownloader extends AsyncTask<Void,Void,String> {

    ProgressDialog pd;
    ListView lv;
    String jsonURL;
    Context context;

    public JSONDownloader(Context c, String jsonURL, ListView lv) {
        this.context = c;
        this.jsonURL = jsonURL;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(context);
        pd.setTitle("Loading Dublin Bikes");
        pd.setMessage("Downloading...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.download();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();
        if(jsonData.startsWith("Error"))
        {
            Toast.makeText(context, jsonData, Toast.LENGTH_SHORT).show();
        }else {
            new JSONParser(context,jsonData, lv).execute();
        }

    }
    private String download()
    {
        Object connection=Connector.connect(jsonURL);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }

        try
        {
            HttpURLConnection httpURLConnection= (HttpURLConnection) connection;
            if(httpURLConnection.getResponseCode()==httpURLConnection.HTTP_OK)
            {
                String line;
                StringBuffer jsonStringData=new StringBuffer();
                InputStream inputStream=new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                while ((line=bufferedReader.readLine()) != null)
                {
                    jsonStringData.append(line+"\n");
                }

                bufferedReader.close();
                inputStream.close();

                return jsonStringData.toString();

            }else
            {
                return "Error: "+httpURLConnection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
    }
}