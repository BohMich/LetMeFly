package com.letmefly.injection;

import android.app.Application;

import androidx.room.Room;

import com.letmefly.MainActivity;
import com.letmefly.databases.AirportsDataBase;
import com.letmefly.databases.daos.AirportEntityDao;
import com.letmefly.repositories.AirportRepository;
import com.letmefly.viewmodels.SearchCountryViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class DatabaseModule {

    @Provides
    @Singleton
    public static AirportsDataBase provideDatabase(Application application
    ) {
        return Room.databaseBuilder(application.getApplicationContext(),
                AirportsDataBase.class, "airport_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    public static AirportEntityDao provideAirportDao(AirportsDataBase database)
    {
        return database.airportDao();
    }

}
