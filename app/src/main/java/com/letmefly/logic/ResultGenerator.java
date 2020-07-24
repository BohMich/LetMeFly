package com.letmefly.logic;

import com.letmefly.databases.entities.AirportEntity;
import com.letmefly.databases.entities.Result;

public class ResultGenerator {

    private static String info_no_data = "NULL";

    //Generates view information based on airport and passenger data.
    private AirportEntity destination;
    private String passengerNationality;

    private Boolean canVisit;
    private Boolean canTransit;
    private Boolean quarantine_required;
    private String details;
    private Result result;

    public ResultGenerator(AirportEntity destination, String passengerNationality){
        this.destination = destination;
        this.passengerNationality = passengerNationality.replaceAll("\\s", "");;

        result = generateResult();
    }

    private Result generateResult(){
        generateVisitBlacklist();
        generateTransitBlacklist();
        generateQuarantineBlacklist();
        generateDetails();

        Result temp = new Result(destination.getCity(),destination.getCountry(),canVisit,canTransit,passengerNationality,details, quarantine_required);

        return temp;

    }
    private void generateVisitBlacklist(){
        canVisit = destination.getCan_visit();

        //get a set of whitelist countries.
        String can_visit_info = destination.getCan_visit_info().replaceAll("\\s", "");
        String[] countries = can_visit_info.split(",");

        if(destination.getCountry().equals(passengerNationality)){
            //passenger can always return to its own country
            canVisit = true;
            return;
        }
        if(countries[0].equals(info_no_data))    //if empty assume boolean
        {
            return;
        }
        else{
            for(int i = 0; i<countries.length; i++){    //iterate through whitelist find nationality match.
                if(countries[i].equals(passengerNationality)){
                    boolean temp = destination.getCan_visit();
                    canVisit = !temp;   //exception country found, override the canVisit boolean.
                }
            }
        }
    }

    private void generateTransitBlacklist(){
        canTransit = destination.getCan_transit();

        //get a set of whitelist countries.
        String can_transit_info = destination.getCan_transit_info().replaceAll("\\s", "");
        String[] countries = can_transit_info.split(",");

        if(countries[0].equals(info_no_data))    //if empty assume boolean
        {
            return;
        }

        //Transit is a blacklist, country found on this list = can't transit.
        for(int i = 0; i<countries.length; i++){    //iterate through blacklist find nationality match.
            if(countries[i].equals(passengerNationality)){
                boolean temp = destination.getCan_transit();
                canTransit = !temp;
            }
        }
    }

    private void generateQuarantineBlacklist(){
        quarantine_required = destination.getQuarantine_required();

        String quarantine_whitelist = destination.getQuarantine_whitelist().replaceAll("\\s", "");
        String[] countries = quarantine_whitelist.split(",");

        if(countries[0].equals(info_no_data))    //if empty assume boolean
        {
            return;
        }

        for(int i = 0; i<countries.length; i++){    //iterate through blacklist find nationality match.
            if(countries[i].equals(passengerNationality)){
                boolean temp = destination.getQuarantine_required();
                quarantine_required = !temp;
            }
        }
    }

    private void generateDetails(){
        String visit_details = destination.getCan_visit_details();
        String transit_details = destination.getCan_transit_details();
        String quarantine_details = destination.getQuarantine_details();

        String details = "";

        if(!visit_details.equals("NULL")) details += visit_details + "\n \n";
        if(!transit_details.equals("NULL")) details += transit_details + "\n \n";
        if(!quarantine_details.equals("NULL")) details += quarantine_details;

        this.details = details;
    }

    public Result getResult(){
        return result;
    }
}
