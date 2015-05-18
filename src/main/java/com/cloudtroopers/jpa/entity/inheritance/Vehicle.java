package com.cloudtroopers.jpa.entity.inheritance;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
 
@Entity
@Table(name = "VEHICLE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "VEHICLE_TYPE")
public abstract class Vehicle {
 
    @TableGenerator(name = "VEHICLE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "VEHICLE_GEN")
    private int idVehicle;
    private String manufacturer;
 
    public int getIdVehicle() {
        return idVehicle;
    }
 
    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }
 
    public String getManufacturer() {
        return manufacturer;
    }
 
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
 
}
