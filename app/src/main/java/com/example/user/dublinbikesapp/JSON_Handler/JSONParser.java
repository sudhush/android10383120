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
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.dublinbikesapp.BikeObject;
import com.example.user.dublinbikesapp.Main2Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser extends AsyncTask<Void,Void,Boolean> {

    ListView lv;
    String jsonData;
    Context context;
    ProgressDialog pd;
    ArrayList<BikeObject> bikes=new ArrayList<>();

    ArrayList bikesLoaded = new ArrayList<String>();

    public JSONParser(Context c, String jsonData, ListView lv) {
        this.context = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(context);
        pd.setTitle("Dublin Bikes");
        pd.setMessage("Loading real time data...Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Boolean isParseDone) {
        super.onPostExecute(isParseDone);

        pd.dismiss();
        if(isParseDone)
        {
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,bikesLoaded);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent bikeDetailsIntent = new Intent(context,Main2Activity.class);
                    BikeObject selectedBike =  bikes.get(i);
                    bikeDetailsIntent.putExtra("AvailableBikes", selectedBike.AvailableBikes);
                    bikeDetailsIntent.putExtra("BikeNumber", selectedBike.BikeNumber);
                    bikeDetailsIntent.putExtra("BikeStands", selectedBike.BikeStands);
                    bikeDetailsIntent.putExtra("LocationAddress", selectedBike.LocationAddress);
                    bikeDetailsIntent.putExtra("LocationName", selectedBike.LocationName);
                    bikeDetailsIntent.putExtra("OpenStatus", selectedBike.OpenStatus);
                    bikeDetailsIntent.putExtra("PaymentTerminal", selectedBike.PaymentTerminal);

                    context.startActivity(bikeDetailsIntent);
                }
            });

        }else
        {
            Toast.makeText(context, "Unable to connect to the server. Please check later.", Toast.LENGTH_SHORT).show();
        }

    }

    private Boolean parse()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;

            bikes.clear();
            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                String number=jo.getString("number");
                String name=jo.getString("name");
                Log.i("name", name);
                bikesLoaded.add(name);

                String address=jo.getString("address");
                String banking=jo.getString("banking");
                String status=jo.getString("status");
                String available_bikes=jo.getString("available_bikes");
                Log.i("available_bikes", available_bikes);
                String available_bike_stands=jo.getString("available_bike_stands");

                BikeObject bikeObject = new BikeObject(number, name, address, banking, status, available_bikes, available_bike_stands);
                bikes.add(bikeObject);

            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
