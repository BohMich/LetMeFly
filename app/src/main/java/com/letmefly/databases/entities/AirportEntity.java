package com.letmefly.databases.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity
public class AirportEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "airport_code")
    private String airport_code;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "city")
    private String city;

    //visit
    @ColumnInfo(name = "can_visit")
    private Boolean can_visit;

    @ColumnInfo(name = "can_visit_info")
    private String can_visit_info;

    //transit
    @ColumnInfo(name = "can_transit")
    private Boolean can_transit;

    @ColumnInfo(name = "can_transit_info")
    private String can_transit_info;

    public AirportEntity(String airport_code, String country, String city, Boolean can_visit, String can_visit_info, Boolean can_transit, String can_transit_info) {
        this.airport_code = airport_code;
        this.country = country;
        this.city = city;
        this.can_visit = can_visit;
        this.can_visit_info = can_visit_info;
        this.can_transit = can_transit;
        this.can_transit_info = can_transit_info;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirport_code() {
        return airport_code;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Boolean getCan_visit() {
        return can_visit;
    }

    public String getCan_visit_info() {
        return can_visit_info;
    }

    public Boolean getCan_transit() {
        return can_transit;
    }

    public String getCan_transit_info() {
        return can_transit_info;
    }


}



