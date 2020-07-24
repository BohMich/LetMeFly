package com.letmefly.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.letmefly.databases.entities.AirportEntity;
import com.letmefly.logic.ResultGenerator;
import com.letmefly.databases.entities.Result;
import com.letmefly.repositories.AirportRepository;

import javax.inject.Inject;


public class SearchCountryViewModel extends AndroidViewModel {

    private final AirportRepository airportsRepo;
    private MutableLiveData<Result> result;

    @Inject
    public SearchCountryViewModel(@NonNull Application application,
                                  AirportRepository repo) {
        super(application);
        this.airportsRepo = repo;
        result = new MutableLiveData<>();
    }

    public void checkCountry(String destination, String departure){
        AirportEntity dest = airportsRepo.getAirportByCountry(destination);

        //Check if data was retrieved.
        if(dest == null){
            result.setValue(null);
            return;
        }
        if(dest.getAirport_code().equals("NUL")){
            result.setValue(null);  //invalid flight code
        }
        else {
            ResultGenerator generator = new ResultGenerator(dest,departure);
            result.setValue(generator.getResult());
        }
    }

    public LiveData<Result> getResult(){
        return result;
    }

}
