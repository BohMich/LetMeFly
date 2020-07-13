package com.letmefly.models;

public class Airport {

    private String country;
    private String city;
    private String airportCode;

    public Airport(String country, String city, String airportCode) {
        this.country = country;
        this.city = city;
        this.airportCode = airportCode;
    }

    public String getAirportCode() {
        return airportCode;
    }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
}
