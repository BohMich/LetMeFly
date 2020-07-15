package com.letmefly.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.letmefly.databases.entities.AirportEntity;
import com.letmefly.logic.ResultGenerator;
import com.letmefly.models.Result;
import com.letmefly.repositories.AirportRepository;


public class PassengerViewModel extends AndroidViewModel {

    //Create a connection with repository to read airport data
    private AirportRepository airportsRepo;
    private MutableLiveData<Result> result;

    public PassengerViewModel(@NonNull Application application) {
        super(application);
        airportsRepo = new AirportRepository(application);
        result = new MutableLiveData<>();
    }
    //private SearchFlightCallBack callback;

    public void checkFlight(String flightNumber, String nationality){
        //check if flight is valid, if it is generate a status regarding the flight.
        String inputCode = flightNumber.substring(0,3).toUpperCase();
        AirportEntity destination = airportsRepo.getAirport(inputCode);

        //Check if data was retrieved.
        if(destination == null){
            result.setValue(null);
            return;
        }
        if(destination.getAirport_code().equals("NUL")){
            result.setValue(null);  //invalid flight code
        }
        else {
            //Correct data retrieved, generate airport result.
            //TODO Run instance of ResultGenerator to update the result model.
            ResultGenerator generator = new ResultGenerator(destination,nationality);

            result.setValue(generator.getResult());
        }
    }

    public LiveData<Result> getResult(){
        return result;
    }

}
