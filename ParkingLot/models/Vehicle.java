package models;

import models.enums.AllowedVehicleType;

public class Vehicle extends BaseModal
{
    private String ownerName;
    private String ownerContactNumber;
    private String registrationNumber;
    private AllowedVehicleType vehicleType;

    public Vehicle(String ownerName, String ownerContactNumber, String registrationNumber, AllowedVehicleType vehicleType) {
        this.ownerName = ownerName;
        this.ownerContactNumber = ownerContactNumber;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
    }

    public AllowedVehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(AllowedVehicleType vehicleType) {
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
