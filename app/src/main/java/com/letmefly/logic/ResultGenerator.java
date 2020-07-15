package com.letmefly.logic;

import com.letmefly.databases.entities.AirportEntity;
import com.letmefly.models.Result;

import java.util.ArrayList;

public class ResultGenerator {
    private static String info_no_data = "NULL";
    //Generates view information based on airport and passenger data.
    private AirportEntity destination;
    private String passengerNationality;

    private Boolean canVisit;
    private ArrayList<String> VisitWhitelist;

    private Boolean canTransit;
    private ArrayList<String> TransitBlackList;

    private Result result;

    public ResultGenerator(AirportEntity destination, String passengerNationality){
        this.destination = destination;
        this.passengerNationality = passengerNationality;

        result = generateResult();
    }

    private Result generateResult(){

        generateVisitWhitelist();
        generateTransitBlacklist();

        Result temp = new Result(destination.getCity(),destination.getCountry(),canVisit,canTransit,passengerNationality);

        return temp;

    }
    public void generateVisitWhitelist(){
        canVisit = destination.getCan_visit();
        ArrayList<String> visitCountries = new ArrayList<>();
        String[] countries = destination.getCan_visit_info().split(",");    //split whitelist

        if(countries.equals(info_no_data))    //if empty assume boolean
        {
            canVisit = destination.getCan_visit();
            return;
        }

        for(int i = 0; i<countries.length; i++){    //iterate through whitelist find nationality match.
            if(countries[i].equals(passengerNationality)){
                canVisit = true;
            }
        }
    }

    public void generateTransitBlacklist(){
        canTransit = destination.getCan_transit();
        ArrayList<String> transitCountries = new ArrayList<>();
        String[] countries = destination.getCan_transit_info().split(",");    //split blacklist

        if(countries.equals(info_no_data))    //if empty assume boolean
        {
            canTransit = destination.getCan_visit();
            return;
        }

        //Transit is a blacklist, country found on this list = can't transit.
        for(int i = 0; i<countries.length; i++){    //iterate through blacklist find nationality match.
            if(countries[i].equals(passengerNationality)){
                canTransit = false;
            }
        }
    }

    public Boolean getCanVisit(){
        return canVisit;
    }
    public Boolean getCanTransit(){
        return canTransit;
    }
    public Result getResult(){
        return result;
    }
}
