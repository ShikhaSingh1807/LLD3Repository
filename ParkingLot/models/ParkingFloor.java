package models;

import models.enums.VehicleType;
import models.enums.ParkingFloorStatus;

import java.util.List;

public class ParkingFloor extends BaseModal
{
    private ParkingFloorStatus parkingFloorStatus;
    private List<ParkingSpot> parkingSpots;
    private List<AllowedVehicle> allowedVehicles;
    private Integer floorNumber;

    public ParkingFloor(Integer floorNumber) {
        this.parkingFloorStatus = ParkingFloorStatus.EMPTY;
        this.floorNumber = floorNumber;
    }

    public ParkingFloorStatus getParkingFloorStatus() {
        return parkingFloorStatus;
    }

    public void setParkingFloorStatus(ParkingFloorStatus parkingFloorStatus) {
        this.parkingFloorStatus = parkingFloorStatus;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public List<AllowedVehicle> getAllowedVehicles() {
        return allowedVehicles;
    }

    public void setAllowedVehicles(List<AllowedVehicle> allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
}
