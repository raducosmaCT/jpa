package com.cloudtroopers.jpa.entity.inheritance;

import javax.persistence.Entity;

@Entity
public class Bike extends PassengerVehicle {
 
    private int saddleHeight;
 
    public int getSaddleHeight() {
        return saddleHeight;
    }
 
    public void setSaddleHeight(int saddleHeight) {
        this.saddleHeight = saddleHeight;
    }
 
}