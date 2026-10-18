package models;

import models.enums.VehicleType;

public class AllowedVehicle extends BaseModal
{
    private VehicleType vehicleType;
    private Integer capacity;
    private Integer availableCapacity;

    public AllowedVehicle(VehicleType vehicleType, Integer capacity) {
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.availableCapacity=capacity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
