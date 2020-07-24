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

import javax.inject.Inject;

import dagger.hilt.DefineComponent;

@Database(entities = {AirportEntity.class}, version = 1, exportSchema = false)
public abstract class AirportsDataBase extends RoomDatabase {
        public abstract AirportEntityDao airportDao();
}



