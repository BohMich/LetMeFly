package com.letmefly.repositories;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

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

public class AirportRepository {

    private AirportEntityDao airportDao;

    public AirportRepository(Application app){
        AirportsDataBase database = AirportsDataBase.getInstance(app);
        airportDao = database.airportDao();

        //refresh database when starting the application
        //TODO Create a system where data is requested from drive if that fails read from local.
        deleteAll();
        fillDataBaseWithBaseData(app.getApplicationContext());
    }

    public AirportEntity getAirport(String airportCode) {
        AirportEntity temp = new AirportEntity("NUL","NULL","NULL",false,
                                                "NULL",false,"NULL","NULL","NULL");
        GetAirportAsyncTask request = new GetAirportAsyncTask(airportDao);

        try
        {
            temp = request.execute(airportCode).get();
        }
        catch(Exception e)
        {

        }
        return temp;
    }

    public void deleteAll(){
        new DeleteAirportAsyncTask(airportDao).execute();
    }

    public void insert(AirportEntity airport){
        new InsertAirportAsyncTask(airportDao).execute(airport);
    }

    //TODO AsyncTask is depreciated, use concurrent instead for 30API
    private static class GetAirportAsyncTask extends AsyncTask<String, Void, AirportEntity> {
        private AirportEntityDao airportDao;
        private GetAirportAsyncTask(AirportEntityDao airportDao){
            this.airportDao = airportDao;
        }

        @Override
        protected AirportEntity doInBackground(String... strings) {
            AirportEntity request = airportDao.findByCode(strings[0]);
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
        boolean canVisit = false;
        boolean canTransit = false;

        try{
            CSVReader reader = new CSVReader(new InputStreamReader(in));
            String[] nl;

            while ((nl = reader.readNext()) != null){
                if(nl[3].equals("TRUE")) canVisit = true;
                else canVisit = false;

                if(nl[5].equals("TRUE")) canTransit = true;
                else canTransit = false;

                data.add(new AirportEntity(nl[0],nl[1],nl[2],canVisit,nl[4],canTransit,nl[6],nl[7],nl[8]));
            }
        }
        catch(IOException e){
        }
        return data;
    }

}
