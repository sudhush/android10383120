/*
Developers:
Siddarth Nair - 10376001
Sudhanva Hukkeri - 10383120
Marcel Kolbus - 10374813
Nitanshu Rehani - 10382675
Supriya Pawar - 10363812
*/
package com.example.user.dublinbikesapp;

public class BikeObject {

    public String BikeNumber;
    public String LocationName;
    public String LocationAddress;
    public String PaymentTerminal;
    public String OpenStatus;
    public String AvailableBikes;
    public String BikeStands;

    public BikeObject(String bikeNumber, String locationName, String locationAddress, String paymentTerminal, String openStatus, String availableBikes, String bikeStands) {
        BikeNumber = bikeNumber;
        LocationName = locationName;
        LocationAddress = locationAddress;
        PaymentTerminal = paymentTerminal;
        OpenStatus = openStatus;
        AvailableBikes = availableBikes;
        BikeStands = bikeStands;
    }
}
