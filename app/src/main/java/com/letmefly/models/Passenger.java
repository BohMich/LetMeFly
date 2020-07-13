package com.letmefly.models;

public class Passenger {

    private String nationality;
    private String flightNo;

    public Passenger(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightNo() { return flightNo; }
    public void setDestination(String flightNo) {
        this.flightNo = flightNo;
    }

}
