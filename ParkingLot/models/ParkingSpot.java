package models;

import models.enums.VehicleType;
import models.enums.ParkingSpotStatus;

public class ParkingSpot extends BaseModal
{
    private ParkingSpotStatus parkingSpotStatus;
    private Integer parkingSpotNumber;
    private VehicleType vehicleTypes;
    private ParkingFloor parkingFloor; // This added to make the count of allowed vehicle increase when spot is freed

    public ParkingSpot(Integer parkingSpotNumber, VehicleType vehicleTypes,ParkingFloor parkingFloor) {
        this.parkingSpotStatus = ParkingSpotStatus.EMPTY;
        this.parkingSpotNumber = parkingSpotNumber;
        this.vehicleTypes = vehicleTypes;
        this.parkingFloor = parkingFloor;
    }

    public VehicleType getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(VehicleType vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public Integer getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(Integer parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public VehicleType getAllowedVehicleType() {
        return vehicleTypes;
    }

    public void setAllowedVehicleType(VehicleType vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }
}
