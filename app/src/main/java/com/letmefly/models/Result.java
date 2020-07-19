package com.letmefly.models;

import android.os.Parcel;
import android.os.Parcelable;


public class Result{
    //certificate is the response that will be passed back to the activity.
    private String city;
    private String country;
    private boolean canVisit;
    private boolean canTransit;
    private String nationality;

    private String details;

    private int resultState;

    public Result(String city, String country, boolean canVisit, boolean canTransit, String nationality, String details) {
        this.city = city;
        this.country = country;
        this.canVisit = canVisit;
        this.canTransit = canTransit;
        this.nationality = nationality;
        this.details = details;

        if(canVisit) resultState = 2;
        else if(canTransit) resultState = 1;
        else resultState = 0;
    }

    public String getDestination() {
        String mes = String.format("Destination: %1$s, %2$s",city,country);
        String temp = "Destination:" + " " + city + ", " + country + ".";
        return temp;
    }

    public String getLine1() {
        //can visit details;
        String mes1 = String.format("Passengers from %1$s",nationality);

        //set state based on result level;
        String mes2 = " ";

        if(resultState == 2) mes2 = String.format(" can enter %1$s.",city);
        else if(resultState == 1) mes2 = String.format(" can not enter %1$s.",city);
        else mes2 = String.format(" are subject to strict entry ban. Do not travel to %1$s.",city);


        String message = mes1 + mes2;
        return message;
    }

    public String getLine2(){
        String mes = "";

        if(resultState == 2) mes = "You can safely visit the city or transfer on the airport.";
        else if(resultState == 1) mes = String.format("You may be allowed to transfer on the airport, but you can not stay in %1$s without quarantine.",country);
        else mes = "Do not transit on the airport, you will be subjected to quarantine.";

        return mes;
    }

    public String getLine3(){
        return details;
    }

    public boolean isCanVisit() {
        return canVisit;
    }

    public boolean isCanTransit() {
        return canTransit;
    }
}
