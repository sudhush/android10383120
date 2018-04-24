/*
Developers:
Siddarth Nair - 10376001
Sudhanva Hukkeri - 10383120
Marcel Kolbus - 10374813
Nitanshu Rehani - 10382675
Supriya Pawar - 10363812
*/

package com.example.user.dublinbikesapp.JSON_Handler;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {

    public static Object connect(String jsonURL)
    {
        try
        {
            URL url=new URL(jsonURL);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);
            return con;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error: "+e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error: "+e.getMessage();
        }
    }

}
