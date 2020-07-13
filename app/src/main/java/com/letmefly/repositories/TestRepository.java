package com.letmefly.repositories;

import androidx.lifecycle.MutableLiveData;

import com.letmefly.models.Airport;

import java.util.ArrayList;

public class TestRepository {

    private static TestRepository instance;

    private ArrayList<Airport> airportsDataSet;

    public static TestRepository getInstance(){
        if(instance == null){
            instance = new TestRepository();
        }
        return instance;
    }

    //data retrieval code
    public MutableLiveData<ArrayList<Airport>> getAirportsDataSet(){
        fillAirportList();

        MutableLiveData<ArrayList<Airport>> data = new MutableLiveData<>();
        data.setValue(airportsDataSet);
        return data;
    }

    private void fillAirportList() {
        airportsDataSet.add(
                new Airport("United Kingdom" , "Edinburgh" , "EDI")
        );
        airportsDataSet.add(
                new Airport("China" , "Zhuhai" , "ZHU")
        );
        airportsDataSet.add(
                new Airport("Finland" , "Helsinki" , "HEL")
        );
    }
}
