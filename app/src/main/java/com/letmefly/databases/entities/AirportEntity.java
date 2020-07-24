package com.letmefly.databases.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    @ColumnInfo(name = "can_visit")
    private Boolean can_visit;

    @ColumnInfo(name = "can_visit_info")
    private String can_visit_info;

    @ColumnInfo(name = "can_transit")
    private Boolean can_transit;

    @ColumnInfo(name = "can_transit_info")
    private String can_transit_info;

    @ColumnInfo(name = "can_visit_details")
    private String can_visit_details;

    @ColumnInfo(name = "can_transit_details")
    private String can_transit_details;

    @ColumnInfo(name = "quarantine_required")
    private Boolean quarantine_required;

    @ColumnInfo(name = "quarantine_whitelist")
    private String quarantine_whitelist;

    @ColumnInfo(name = "quarantine_details")
    private String quarantine_details;

    public AirportEntity(String airport_code, String country, String city, Boolean can_visit,
                         String can_visit_info, Boolean can_transit, String can_transit_info,
                         String can_visit_details,String can_transit_details, Boolean quarantine_required,String quarantine_whitelist, String quarantine_details) {

        this.airport_code = airport_code;
        this.country = country;
        this.city = city;
        this.can_visit = can_visit;
        this.can_visit_info = can_visit_info;
        this.can_transit = can_transit;
        this.can_transit_info = can_transit_info;
        this.can_visit_details = can_visit_details;
        this.can_transit_details = can_transit_details;
        this.quarantine_required = quarantine_required;
        this.quarantine_whitelist = quarantine_whitelist;
        this.quarantine_details = quarantine_details;
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

    public String getCan_visit_details() {
        return can_visit_details;
    }

    public String getCan_transit_details() {
        return can_transit_details;
    }

    public Boolean getQuarantine_required() {
        return quarantine_required;
    }

    public String getQuarantine_whitelist() {
        return quarantine_whitelist;
    }

    public String getQuarantine_details() {
        return quarantine_details;
    }
}



