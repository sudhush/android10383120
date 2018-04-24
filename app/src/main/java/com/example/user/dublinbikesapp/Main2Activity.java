/*
Developers:
Siddarth Nair - 10376001
Sudhanva Hukkeri - 10383120
Marcel Kolbus - 10374813
Nitanshu Rehani - 10382675
Supriya Pawar - 10363812
*/

package com.example.user.dublinbikesapp;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Dublin Bikes");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorOrange)));

        Intent bikedetails = getIntent();

        TextView textLocation = (TextView) findViewById(R.id.tvaddress);
        TextView textLocationAddress = (TextView) findViewById(R.id.bikeaddress);
        TextView textBikeOpenStatus = (TextView) findViewById(R.id.bikeOpenStatus);
        TextView textPaymentTerminal = (TextView) findViewById(R.id.bikePaymentTerminal);
        TextView textAvailableBikes = (TextView) findViewById(R.id.availableBikes);
        TextView textBikeStands = (TextView) findViewById(R.id.bikeStands);

        String bikeLocation = bikedetails.getStringExtra("LocationName");
        Log.i("LocationName", bikeLocation);
        textLocation.setText(bikeLocation);

        String locationAddress = bikedetails.getStringExtra("LocationAddress");
        String bikeOpenStatus = bikedetails.getStringExtra("OpenStatus");
        String bikePaymentTerminal = bikedetails.getStringExtra("PaymentTerminal");
        String bikeStands = bikedetails.getStringExtra("BikeStands");
        String availableBikes = bikedetails.getStringExtra("AvailableBikes");
        Log.i("AvailableBikes", availableBikes);

        textLocationAddress.setText(locationAddress);
        textBikeOpenStatus.setText(bikeOpenStatus);
        textPaymentTerminal.setText(bikePaymentTerminal);
        textBikeStands.setText(bikeStands);
        textAvailableBikes.setText(availableBikes);
    }
}
