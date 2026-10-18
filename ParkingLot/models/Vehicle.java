package models;

import models.enums.VehicleType;

public class Vehicle extends BaseModal
{
    private String ownerName;
    private String ownerContactNumber;
    private String registrationNumber;
    private VehicleType vehicleType;

    public Vehicle(String ownerName, String ownerContactNumber, String registrationNumber, VehicleType vehicleType) {
        this.ownerName = ownerName;
        this.ownerContactNumber = ownerContactNumber;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContactNumber() {
        return ownerContactNumber;
    }

    public void setOwnerContactNumber(String ownerContactNumber) {
        this.ownerContactNumber = ownerContactNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
