package com.cloudtroopers.jpa.entity.inheritance;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle {
 
    private int noOfpassengers;
 
    public int getNoOfpassengers() {
        return noOfpassengers;
    }
 
    public void setNoOfpassengers(int noOfpassengers) {
        this.noOfpassengers = noOfpassengers;
    }
 
}