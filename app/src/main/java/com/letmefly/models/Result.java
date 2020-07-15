package com.letmefly.models;

import androidx.lifecycle.LiveData;

import com.letmefly.databases.entities.AirportEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Result {
    //certificate is the response that will be passed back to the activity.
    private String airport;
    private String city;
    private String country;
    private boolean canVisit;
    private boolean canTransit;
    private String nationality;

    public Result(){}

    public Result(String city, String country, boolean canVisit, boolean canTransit, String nationality) {
        this.city = city;
        this.country = country;
        this.canVisit = canVisit;
        this.canTransit = canTransit;
        this.nationality = nationality;
    }

    public String getInfo(){
        return city + " Can Visit: " + canVisit + "  Can transit: " + canTransit;
    }

}
