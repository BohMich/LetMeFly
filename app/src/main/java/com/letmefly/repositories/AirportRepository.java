package com.letmefly.repositories;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.letmefly.R;
import com.letmefly.databases.AirportsDataBase;
import com.letmefly.databases.daos.AirportEntityDao;
import com.letmefly.databases.entities.AirportEntity;
import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

public class AirportRepository {

    private final AirportsDataBase database;
    private final AirportEntityDao airportDao;

    @Inject
    public AirportRepository(Application app, AirportsDataBase database){
        this.database = database;
        this.airportDao = this.database.airportDao();
        //refresh database when starting the application
        //TODO Create a system where data is requested from drive if that fails read from local.
        deleteAll();
        fillDataBaseWithBaseData(app.getApplicationContext());
    }

    public AirportEntity getAirportByCountry(String destination){
        AirportEntity temp = null;
        String country  = destination.replaceAll("\\s", "");
        GetCountryAsyncTask request = new GetCountryAsyncTask(airportDao);

        try{
            temp = request.execute(country).get();
        }
        catch(Exception ignored){

        }
        return temp;
    }

    public void deleteAll(){
        new DeleteAirportAsyncTask(airportDao).execute();
    }

    public void insert(AirportEntity airport){
        new InsertAirportAsyncTask(airportDao).execute(airport);
    }

    private static class GetCountryAsyncTask extends AsyncTask<String, Void, AirportEntity> {
        private AirportEntityDao airportDao;
        private GetCountryAsyncTask(AirportEntityDao airportDao){
            this.airportDao = airportDao;
        }

        @Override
        protected AirportEntity doInBackground(String... strings) {
            AirportEntity request = airportDao.findByCountry(strings[0]);
            return request;
        }
    }

    private static class InsertAirportAsyncTask extends AsyncTask<AirportEntity, Void, Void>{
        private AirportEntityDao airportDao;

        private InsertAirportAsyncTask(AirportEntityDao airportDao){
            this.airportDao = airportDao;
        }

        @Override
        protected Void doInBackground(AirportEntity... airportEntities) {
            airportDao.insert(airportEntities[0]);
            return null;
        }
    }
    private static class DeleteAirportAsyncTask extends AsyncTask<Void , Void , Void >{
        private AirportEntityDao airportDao;

        private DeleteAirportAsyncTask(AirportEntityDao airportDao){
            this.airportDao = airportDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            airportDao.deleteAll();
            return null;
        }
    }


    private void fillDataBaseWithBaseData(Context context){
        List<AirportEntity> airports = CSVLoad(context);
        try {
            for (int i = 0; i < airports.size(); i++){
                insert(airports.get(i));
                Log.d("AIRPORTNO",  String.valueOf(i));
            }
        }catch(Exception e){

        }
    }

    private static List<AirportEntity> CSVLoad(Context context){
        InputStream in = context.getResources().openRawResource(R.raw.airport_data);
        List<AirportEntity> data = new ArrayList<>();

        try{
            CSVReader reader = new CSVReader(new InputStreamReader(in));
            //skip header
            reader.readNext();
            String[] nl;

            while ((nl = reader.readNext()) != null){
                data.add(new AirportEntity(nl[0],nl[1],nl[2],Boolean.parseBoolean(nl[3]),nl[4],
                        Boolean.parseBoolean(nl[5]),nl[6],nl[7],nl[8],Boolean.parseBoolean(nl[9]),
                        nl[10],nl[11]));
            }
        }
        catch(IOException e){
        }
        return data;
    }



}

