package com.letmefly.databases.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.letmefly.databases.entities.AirportEntity;

import java.util.List;

@Dao
public interface AirportEntityDao {
    @Query("SELECT * FROM airportentity")
    List<AirportEntity> getAll();

    @Query("SELECT * FROM airportentity WHERE airport_code = :code")
    AirportEntity findByCode(String code);

    @Query("SELECT * FROM airportentity WHERE country = :country")
    AirportEntity findByCountry(String country);

    @Query("DELETE FROM airportentity")
    void deleteAll();

    @Insert
    void insertAll(AirportEntity... airports);

    @Insert
    void insert(AirportEntity airport);

    @Update
    void update(AirportEntity airport);

    @Delete
    void delete(AirportEntity airport);


}