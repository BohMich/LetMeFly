package com.letmefly.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.letmefly.R;
import com.letmefly.databases.daos.AirportEntityDao;
import com.letmefly.databases.entities.AirportEntity;

@Database(entities = {AirportEntity.class}, version = 1)
public abstract class AirportsDataBase extends RoomDatabase {

        private static AirportsDataBase instance;

        public abstract AirportEntityDao airportDao();

        public static synchronized AirportsDataBase getInstance(Context context){
            if(instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AirportsDataBase.class, "airport_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            }
            return instance;
        }

        private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new PopulateDBAsyncTask(instance).execute();
            }
        };

        private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {
            private AirportEntityDao airportDao;

            private PopulateDBAsyncTask(AirportsDataBase db){
                this.airportDao = db.airportDao();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                //perform initial action.
                return null;
            }
        }


}



